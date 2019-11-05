package com.yasin.hosain.dynamicviewusingjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Typeface;
import android.text.InputType;
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


    public void createTextView(DYTextView dyTextView) {
        TextView textView = new TextView(this);
        textView.setTextSize(dyTextView.getTextSize());
        ViewGroup.LayoutParams layoutParams=setParams(dyTextView.getHeight(),dyTextView.getWidth(),dyTextView.getMargin());
        textView.setLayoutParams(layoutParams);
        setPadding(textView,dyTextView.getPadding());
        setGravity(textView,dyTextView.getGravity());
        setTextStyle(textView,dyTextView.getTextStyle());
        textView.setText(dyTextView.getText());
        parentLayout.addView(textView);
        views.add(dyTextView);
    }




    private void createButton(DYButton dyButton) {

        Button button = new Button(this);
        button.setLayoutParams(setParams(dyButton.getHeight(),dyButton.getWidth(),dyButton.getMargin()));
        setPadding(button,dyButton.getPadding());
        button.setText(dyButton.getName());
        setGravity(button,dyButton.getGravity());
        button.setOnClickListener(this);
        parentLayout.addView(button);
        views.add(dyButton);

    }

    private void createImageView(DYImageView dyImageView) {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.show();

        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams  layoutParams= (LinearLayout.LayoutParams) setParams(dyImageView.getHeight(),dyImageView.getWidth(),dyImageView.getMargin());
        switch (dyImageView.getGravity()){
            case  "center": layoutParams.gravity=Gravity.CENTER;break;
            case  "left": layoutParams.gravity=Gravity.LEFT;break;
            case  "right": layoutParams.gravity=Gravity.RIGHT;break;
            default:layoutParams.gravity=Gravity.LEFT;break;
        }
        imageView.setLayoutParams(layoutParams);
        setPadding(imageView,dyImageView.getPadding());
        Picasso.get().load(dyImageView.getImageSource()).error(android.R.mipmap.sym_def_app_icon).into(imageView, new Callback() {
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


    public void createEditText(DYEditText dyEditText) {
        EditText editText = new EditText(this);
        editText.setMinimumWidth(getDimension(R.dimen.dp200));
        editText.setInputType(setInputType(dyEditText.getInputType()));
        editText.setHint(dyEditText.getHint());
        if (dyEditText.isSingleLine()) {
            editText.setMaxLines(1);
            editText.setSingleLine();
            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            editText.setHorizontallyScrolling(true);
        } else {
            editText.setLines(5);
        }
        editText.setLayoutParams(setParams(dyEditText.getHeight(),dyEditText.getWidth(),dyEditText.getMargin()));
        setPadding(editText,dyEditText.getPadding());
        setGravity(editText,dyEditText.getGravity());
        setTextStyle(editText,dyEditText.getTextStyle());
        parentLayout.addView(editText);
        views.add(dyEditText);
    }

    public void createRadioGroup(DYRadioGroup dyRadioGroup) {
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setLayoutParams(setParams(dyRadioGroup.getHeight(),dyRadioGroup.getWidth(),dyRadioGroup.getMargin()));
        setPadding(radioGroup,dyRadioGroup.getPadding());
        switch (dyRadioGroup.getGravity()){
            case  "center": radioGroup.setGravity(Gravity.CENTER);break;
            case  "left": radioGroup.setGravity(Gravity.LEFT);break;
            case  "right": radioGroup.setGravity(Gravity.RIGHT);break;
            default:radioGroup.setGravity(Gravity.LEFT);break;
        }
        RadioButton radioButton;
        for (String option : dyRadioGroup.getOptions()) {
            radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }
        parentLayout.addView(radioGroup);
        views.add(dyRadioGroup);
    }



    public void createCheckbox(DYCheckbox dyCheckbox) {
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(dyCheckbox.getDescription());
        checkBox.setLayoutParams(setParams(dyCheckbox.getHeight(),dyCheckbox.getWidth(),dyCheckbox.getMargin()));
        setPadding(checkBox,dyCheckbox.getPadding());
        setGravity(checkBox,dyCheckbox.getGravity());
        parentLayout.addView(checkBox);
        views.add(dyCheckbox);
    }


//    public void createCheckboxGroup(DYCheckboxGroup dyCheckboxGroup) {
//        TextView textView = new TextView(this);
//        textView.setText(dyCheckboxGroup.getDescription());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, getDimension(R.dimen.dp20), 0, getDimension(R.dimen.dp10));
//        textView.setLayoutParams(params);
//        parentLayout.addView(textView);
//
//        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        for (int i = 0; i < dyCheckboxGroup.getOptions().size(); ++i) {
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(dyCheckboxGroup.getOptions().get(i));
//            if (i == dyCheckboxGroup.getOptions().size() - 1)
//                params.setMargins(0, 0, 0, getDimension(R.dimen.dp20));
//            checkBox.setLayoutParams(params);
//            parentLayout.addView(checkBox);
//        }
//        views.add(dyCheckboxGroup);
//    }


    public void createDropDownList(DYDropDownList dyDropDownList) {

        Spinner spinner = new Spinner(this);
        spinner.setLayoutParams(setParams(dyDropDownList.getHeight(),dyDropDownList.getWidth(),dyDropDownList.getMargin()));
        setPadding(spinner,dyDropDownList.getPadding());
        switch (dyDropDownList.getGravity()){
            case  "center": spinner.setGravity(Gravity.CENTER);break;
            case  "left": spinner.setGravity(Gravity.LEFT);break;
            case  "right": spinner.setGravity(Gravity.RIGHT);break;
            default:spinner.setGravity(Gravity.LEFT);break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.buildformer_spinner_item);
        for (String option : dyDropDownList.getOptions()) {
            adapter.add(option);
        }
        spinner.setAdapter(adapter);
        spinner.setTag(dyDropDownList.getTag());
        parentLayout.addView(spinner);
        views.add(dyDropDownList);
    }
    public void createFromJson(String jsonString) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JSONFeed feed = mapper.readValue(jsonString, JSONFeed.class);
        List<DYView> views = feed.getViews();
        for (DYView view : views) {
            String type = view.getType();
            if (type.equals(Constants.TYPE_TEXT_VIEW)) {
                DYTextView dyTextView = (DYTextView) view;
                createTextView(dyTextView);
            } else if (type.equals(Constants.TYPE_EDIT_TEXT)) {
                DYEditText dyEditText = (DYEditText) view;
                createEditText(dyEditText);
            } else if (type.equals(Constants.TYPE_CHECKBOX)) {
                DYCheckbox dyCheckbox = (DYCheckbox) view;
                createCheckbox(dyCheckbox);
            } else if (type.equals(Constants.TYPE_RADIO_GROUP)) {
                DYRadioGroup dyRadioGroup = (DYRadioGroup) view;
                createRadioGroup(dyRadioGroup);
            } else if (type.equals(Constants.TYPE_DROP_DOWN_LIST)) {
                DYDropDownList dyDropDownList = (DYDropDownList) view;
                createDropDownList(dyDropDownList);
            }else if (type.equals(Constants.TYPE_BUTTON)) {
                DYButton dyButton= (DYButton) view;
                createButton(dyButton);
            }else if (type.equals(Constants.TYPE_IMAGE_VIEW)) {
                DYImageView dyImageView= (DYImageView) view;
                createImageView(dyImageView);
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

    private ViewGroup.LayoutParams setParams(String height, String width, String margin) {

        int viewHeight=0;
        int viewWidth=0;

        LinearLayout.LayoutParams params = null;
        if (height!=null&&width!=null&&margin!=null){

            if (isItDigit(height)||isItDigit(width)){
                if (isItDigit(height)){
                    viewHeight=Integer.parseInt(height);
                }else {
                    viewHeight=ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                if (isItDigit(width)){
                    viewWidth=Integer.parseInt(width);
                }else {
                    viewWidth=ViewGroup.LayoutParams.WRAP_CONTENT;
                }
            }
            if (height.equals("wrap_content")){
                viewHeight=ViewGroup.LayoutParams.WRAP_CONTENT;
            }
            if (width.equals("wrap_content")){
                viewWidth=ViewGroup.LayoutParams.WRAP_CONTENT;
            }
            if (height.equals("match_parent")){
                viewHeight=ViewGroup.LayoutParams.MATCH_PARENT;
            }
            if (width.equals("match_parent")){
                viewWidth=ViewGroup.LayoutParams.MATCH_PARENT;
            }

            params=new LinearLayout.LayoutParams(viewWidth, viewHeight);
        }else {
            params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (margin!=null){
            params.setMargins(0,0,0,0);
        }else {
            if (isItDigit(margin)){
                params.setMargins(Integer.parseInt(margin),Integer.parseInt(margin),Integer.parseInt(margin),Integer.parseInt(margin));
            }else {
                params.setMargins(0,0,0,0);
            }
        }
        return params;
    }

    private void setPadding(View view, String padding) {
        if (padding!=null){
            if (isItDigit(padding)){
                view.setPadding(Integer.parseInt(padding),Integer.parseInt(padding),Integer.parseInt(padding),Integer.parseInt(padding));
            }else {
                view.setPadding(0,0,0,0);
            }
        }
    }

    private void setGravity(TextView view, String gravity) {
        if (gravity!=null){
            switch (gravity){
                case  "center": view.setGravity(Gravity.CENTER);break;
                case  "left": view.setGravity(Gravity.LEFT);break;
                case  "right": view.setGravity(Gravity.RIGHT);break;
                default:view.setGravity(Gravity.LEFT);break;
            }
        }
    }

    private void setTextStyle(TextView textView, String textStyle) {
        if (textStyle!=null){
            switch (textStyle){
                case"bold":textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
                case"italic":textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
                case"normal":textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
                default:textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    private int setInputType(String inputType) {
        switch (inputType){
            case "text":return InputType.TYPE_CLASS_TEXT;
            case "number":return InputType.TYPE_CLASS_NUMBER;
            case "phone":return InputType.TYPE_CLASS_PHONE;
            default:return InputType.TYPE_NULL;
        }
    }


    private boolean isItDigit(String digitString){
        try {
            Integer.parseInt(digitString);
            return true;
        }catch (NumberFormatException n){
            n.printStackTrace();
            return false;
        }
    }



}
