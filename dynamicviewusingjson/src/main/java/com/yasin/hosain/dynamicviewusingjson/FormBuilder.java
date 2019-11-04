package com.yasin.hosain.dynamicviewusingjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yasin.hosain.dynamicviewusingjson.model.DYButton;
import com.yasin.hosain.dynamicviewusingjson.model.DYCheckbox;
import com.yasin.hosain.dynamicviewusingjson.model.DYCheckboxGroup;
import com.yasin.hosain.dynamicviewusingjson.model.DYDropDownList;
import com.yasin.hosain.dynamicviewusingjson.model.DYEditText;
import com.yasin.hosain.dynamicviewusingjson.model.DYImageView;
import com.yasin.hosain.dynamicviewusingjson.model.DYRadioGroup;
import com.yasin.hosain.dynamicviewusingjson.model.DYTextView;
import com.yasin.hosain.dynamicviewusingjson.model.DYView;
import com.yasin.hosain.dynamicviewusingjson.model.JSONFeed;
import com.yasin.hosain.dynamicviewusingjson.utils.Constants;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormBuilder extends ContextWrapper implements View.OnClickListener {
    private LinearLayout parentLayout;
    public static final int EDIT_TEXT_MODE_HINT = 1;
    public static final int EDIT_TEXT_MODE_SEPARATE = 2;
    private List<DYView> views;

    public FormBuilder(Context context, LinearLayout parentLayout) {
        super(context);
        this.parentLayout = parentLayout;
        this.views = new ArrayList<>();
    }


    public void createTextView(String text, int textSize) {
        TextView textView = new TextView(this);
        textView.setTextSize(textSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);

        DYTextView DYTextView = new DYTextView();
        DYTextView.setType(Constants.TYPE_TEXT_VIEW);
        DYTextView.setText(text);
        DYTextView.setTextSize(textSize);
        views.add(DYTextView);
    }

    private void createButton(String name) {

        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        button.setLayoutParams(params);
        button.setText(name);
        button.setOnClickListener(this);
        parentLayout.addView(button);

        DYButton dyButton = new DYButton();
        dyButton.setType(Constants.TYPE_BUTTON);
        dyButton.setName(name);
        views.add(dyButton);

    }

    private void createImageView(String url, String gravity) {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.show();

        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        if (gravity.equals("center")){
            params.gravity=Gravity.CENTER;
        }
        imageView.setLayoutParams(params);
        Picasso.get().load(url).error(android.R.mipmap.sym_def_app_icon).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressDialog.dismiss();

            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
        });
        parentLayout.addView(imageView);

        DYImageView dyImageView = new DYImageView();
        dyImageView.setType(Constants.TYPE_IMAGE_VIEW);
        dyImageView.setImageSource(url);
        views.add(dyImageView);

    }


    public void createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setTextSize(16);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        textView.setLayoutParams(params);
        textView.setText(text);
        parentLayout.addView(textView);

        DYTextView DYTextView = new DYTextView();
        DYTextView.setType(Constants.TYPE_TEXT_VIEW);
        DYTextView.setText(text);
        DYTextView.setTextSize(16);
        views.add(DYTextView);
    }


    public void createEditText(String description, int mode, boolean singleLine) {
        EditText editText = new EditText(this);
        editText.setMinimumWidth(getDimension(R.dimen.dp200));
        LinearLayout.LayoutParams params = null;
        if (mode == EDIT_TEXT_MODE_HINT) {
            editText.setHint(description);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        } else if (mode == EDIT_TEXT_MODE_SEPARATE) {
            TextView textView = new TextView(this);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
            textView.setLayoutParams(params);
            textView.setText(description);
            parentLayout.addView(textView);

            params = new LinearLayout.LayoutParams(getDimension(R.dimen.dp200), ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        }
        if (singleLine) {
            editText.setMaxLines(1);
            editText.setSingleLine();
            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            editText.setHorizontallyScrolling(true);
        } else {
            editText.setLines(5);
        }
        editText.setLayoutParams(params);
        parentLayout.addView(editText);

        DYEditText DYEditText = new DYEditText();
        DYEditText.setType(Constants.TYPE_EDIT_TEXT);
        DYEditText.setDescription(description);
        DYEditText.setMode(mode);
        DYEditText.setSingleLine(singleLine);
        views.add(DYEditText);
    }


    public void createRadioGroup(String description, List<String> options) {
        TextView textView = new TextView(this);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);

        RadioGroup radioGroup = new RadioGroup(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20), getDimension(R.dimen.dp20));
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setLayoutParams(params);

        RadioButton radioButton;
        for (String option : options) {
            radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }

        parentLayout.addView(textView);
        parentLayout.addView(radioGroup);

        DYRadioGroup DYRadioGroup = new DYRadioGroup();
        DYRadioGroup.setType(Constants.TYPE_RADIO_GROUP);
        DYRadioGroup.setDescription(description);
        DYRadioGroup.setOptions(options);
        views.add(DYRadioGroup);
    }



    public void createCheckbox(String description) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp20));
        checkBox.setLayoutParams(params);
        parentLayout.addView(checkBox);

        DYCheckbox DYCheckbox = new DYCheckbox();
        DYCheckbox.setType(Constants.TYPE_CHECKBOX);
        DYCheckbox.setDescription(description);
        views.add(DYCheckbox);
    }


    public void createCheckboxGroup(String description, List<String> options) {
        TextView textView = new TextView(this);
        textView.setText(description);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
        textView.setLayoutParams(params);
        parentLayout.addView(textView);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < options.size(); ++i) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(options.get(i));
            if (i == options.size() - 1)
                params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
            checkBox.setLayoutParams(params);
            parentLayout.addView(checkBox);
        }

        DYCheckboxGroup DYCheckboxGroup = new DYCheckboxGroup();
        DYCheckboxGroup.setType(Constants.TYPE_CHECKBOX_GROUP);
        DYCheckboxGroup.setDescription(description);
        DYCheckboxGroup.setOptions(options);
        views.add(DYCheckboxGroup);
    }


    public void createDropDownList(String tag,String description, List<String> options) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10), 0);
        textView.setLayoutParams(params);
        textView.setText(description);
        parentLayout.addView(textView);

        Spinner spinner = new Spinner(this);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
        spinner.setLayoutParams(params);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.buildformer_spinner_item);
        for (String option : options) {
            adapter.add(option);
        }
        spinner.setAdapter(adapter);
        spinner.setTag(tag);
        parentLayout.addView(spinner);

        DYDropDownList DYDropDownList = new DYDropDownList();
        DYDropDownList.setTag(tag);
        DYDropDownList.setType(Constants.TYPE_DROP_DOWN_LIST);
        DYDropDownList.setDescription(description);
        DYDropDownList.setOptions(options);
        views.add(DYDropDownList);
    }
    public void createFromJson(String jsonString) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JSONFeed feed = mapper.readValue(jsonString, JSONFeed.class);
        List<DYView> views = feed.getViews();
        for (DYView view : views) {
            String type = view.getType();
            if (type.equals(Constants.TYPE_TEXT_VIEW)) {
                DYTextView DYTextView = (DYTextView) view;
                createTextView(DYTextView.getText(), DYTextView.getTextSize());
            } else if (type.equals(Constants.TYPE_EDIT_TEXT)) {
                DYEditText DYEditText = (DYEditText) view;
                createEditText(DYEditText.getDescription(), DYEditText.getMode(), DYEditText.isSingleLine());
            } else if (type.equals(Constants.TYPE_CHECKBOX)) {
                DYCheckbox DYCheckbox = (DYCheckbox) view;
                createCheckbox(DYCheckbox.getDescription());
            } else if (type.equals(Constants.TYPE_CHECKBOX_GROUP)) {
                DYCheckboxGroup DYCheckboxGroup = (DYCheckboxGroup) view;
                createCheckboxGroup(DYCheckboxGroup.getDescription(), DYCheckboxGroup.getOptions());
            } else if (type.equals(Constants.TYPE_RADIO_GROUP)) {
                DYRadioGroup DYRadioGroup = (DYRadioGroup) view;
                createRadioGroup(DYRadioGroup.getDescription(), DYRadioGroup.getOptions());
            } else if (type.equals(Constants.TYPE_DROP_DOWN_LIST)) {
                DYDropDownList DYDropDownList = (DYDropDownList) view;
                createDropDownList(DYDropDownList.getTag(),DYDropDownList.getDescription(), DYDropDownList.getOptions());
            }else if (type.equals(Constants.TYPE_BUTTON)) {
                DYButton dyButton= (DYButton) view;
                createButton(dyButton.getName());
            }else if (type.equals(Constants.TYPE_IMAGE_VIEW)) {
                DYImageView dyImageView= (DYImageView) view;
                createImageView(dyImageView.getImageSource(),dyImageView.getGravity());
            }
        }
    }

    private int getDimension(int id) {
        return (int) getResources().getDimension(id);
    }

    @Override
    public void onClick(View view) {

        for (DYView dyView:views) {

            if (dyView.getType().equals(Constants.TYPE_DROP_DOWN_LIST)){
                Spinner spinner=parentLayout.findViewWithTag(dyView.getTag());
                String item= (String) spinner.getSelectedItem();
                Toast.makeText(FormBuilder.this, item, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
