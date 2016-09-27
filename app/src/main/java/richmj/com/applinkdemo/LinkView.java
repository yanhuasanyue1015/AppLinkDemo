package richmj.com.applinkdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张磊 on 2016/9/26.
 */
public class LinkView extends FrameLayout implements ILinkView {
    private TextCrawler textCrawler = new TextCrawler();
    private ImageView ivDefaultPhoto;
    private ImageView ivPhoto;
    private ImageView ivDelete;
    private TextView tvTitle;
    private TextView tvDesc;
    private String url;

    public LinkView(Context context) {
        super(context);
        initView(context);
    }

    public LinkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LinkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.linkview, this);
        setVisibility(GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivDefaultPhoto = (ImageView) findViewById(R.id.ivDefaultPhoto);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        ivDelete = (ImageView) findViewById(R.id.ivDelete);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        addOnClickListener(onClickListener, ivDelete);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivDelete:
                    setVisibility(GONE);
                    break;
            }
        }
    };

    private void addOnClickListener(OnClickListener onClickListener, View... views) {
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void setUrl(String url) {

        this.url = url;
        textCrawler.makePreview(new LinkPreviewCallback() {
            @Override
            public void onPre() {
            }

            @Override
            public void onPos(SourceContent sourceContent, boolean isNull) {
                if (!(isNull)) {
                    tvTitle.setText(sourceContent.getTitle());
                    tvDesc.setText(sourceContent.getDescription());
                    setIv(sourceContent.getImages(), 0);
                }
            }
        }, url);
    }

    @Override
    public String getUrl() {
        int visibility = getVisibility();
        if (visibility != VISIBLE) {
            throw new IllAccessException();
        }
        return url;
    }

    private void setIv(final List<String> urls, final int position) {
        if (urls == null || urls.size() == 0) {
            setVisibility(VISIBLE);
            ivPhoto.setVisibility(GONE);
            ivDefaultPhoto.setVisibility(VISIBLE);
        } else {
            if (urls.size() <= position) {
                return;
            }
            ivPhoto.setVisibility(VISIBLE);
            ivDefaultPhoto.setVisibility(GONE);
            Picasso.with(getContext()).load(urls.get(position)).into(ivPhoto, new Callback() {
                @Override
                public void onSuccess() {
                    i("height:" + ivPhoto.getHeight());
                    i("width:" + ivPhoto.getWidth());
                    setVisibility(VISIBLE);
                }

                @Override
                public void onError() {
                    int tempPosition = position + 1;
                    setIv(urls, tempPosition);
                }
            });
        }
    }


    private void i(String content) {
        Log.i(getClass().getName(), content);
    }
}
