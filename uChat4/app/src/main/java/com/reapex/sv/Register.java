package com.reapex.sv;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reapex.sv.db.AChatDB;
import com.reapex.sv.db.AUser;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.reapex.sv.MyUtil.validatePassword;

public class Register extends AppCompatActivity implements View.OnClickListener {

    AChatDB db;

    private static final int UPDATE_AVATAR_BY_ALBUM = 2;
    private View mLayout;

    String mImageName, mAvatarUri;

    ImageView mAgreementIv, mAvatarSdv;
    EditText  mNickNameEt, mPhoneEt, mPasswordEt;
    Button    mRegisterBtn;

    boolean mAgree;   //是否同意协议

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUtil.hideSystemUI(this);

        setContentView(R.layout.a_register);
        mLayout = findViewById(R.id.a_register);
        db = AChatDB.getDatabase(this);

        mRegisterBtn    = findViewById(R.id.btn_register);

        mAvatarSdv      = findViewById(R.id.iv_avatar);
        mAvatarSdv.setImageResource(R.mipmap.default_user_avatar);

        mAgreementIv    = findViewById(R.id.image_view_agree);
        mNickNameEt     = findViewById(R.id.et_nick_name);
        mNickNameEt.setText(getString(R.string.uchat));
        mPhoneEt        = findViewById(R.id.edit_text_phone);
        mPasswordEt     = findViewById(R.id.edit_text_password);

        TextView mAgreementTv = findViewById(R.id.text_view_agreement);
        TextView mPrivacy     = findViewById(R.id.text_view_privacy);

        mAvatarSdv.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mAgreementIv.setOnClickListener(this);
        mAgreementTv.setOnClickListener(this);
        mPrivacy.setOnClickListener(this);

        mNickNameEt.addTextChangedListener(new TextChange());
        mPhoneEt.addTextChangedListener(new TextChange());
        mPasswordEt.addTextChangedListener(new TextChange());

        //permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Log.e("Register", "permission granted.");
        }else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            showExplanation(getString(R.string.request_permission_title), getString(R.string.permission_rationale_storage), Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void showExplanation(String title, String message, final String permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });
        builder.create().show();
    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Toast.makeText(this, getString(R.string.request_permission_granted_storage), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.permission_rationale_storage), Toast.LENGTH_LONG).show();
        }
    });

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {
            String nickName = mNickNameEt.getText().toString();
            String phone    = mPhoneEt.getText().toString();
            String password = mPasswordEt.getText().toString();
            if(!MyUtil.isValidChinesePhone(phone)) {
                Snackbar sb = Snackbar.make(mLayout, getString(R.string.phone_wrong), Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(Color.WHITE);
                sb.getView().findViewById(com.google.android.material.R.id.snackbar_text).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                sb.setTextColor(Color.RED).setAnchorView(findViewById(R.id.edit_text_password));
                sb.show();
            }else if (!validatePassword(password)) {
                Snackbar sb = Snackbar.make(mLayout, getString(R.string.password_rules), Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(Color.WHITE);
                sb.getView().findViewById(com.google.android.material.R.id.snackbar_text).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                sb.setTextColor(Color.RED).setAnchorView(findViewById(R.id.btn_register));
                sb.show();
            }else {
                register(nickName, phone, password, mAvatarUri);
            }
        }else if (v.getId() == R.id.image_view_agree){
            if (mAgree) {
                mAgreementIv.setBackgroundResource(R.mipmap.icon_choose_false);
                mAgree = false;
                Snackbar sb = Snackbar.make(mLayout, getString(R.string.global_agree), Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_error));
                sb.getView().findViewById(com.google.android.material.R.id.snackbar_text).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                sb.setTextColor(Color.WHITE).setAnchorView(findViewById(R.id.image_view_agree));
                sb.show();
            } else {
                mAgreementIv.setBackgroundResource(R.mipmap.icon_choose_true);
                mAgree = true;
            }
            checkSubmit();
        }else if (v.getId() == R.id.iv_avatar){
            Intent intent;
            intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, UPDATE_AVATAR_BY_ALBUM);
        }else if (v.getId() == R.id.text_view_agreement){
            Intent intent = new Intent(this, MyWeb.class);
            intent.putExtra("from", "agreement");
            startActivity(intent);
        }else if (v.getId() == R.id.text_view_privacy){
            Intent intent = new Intent(this, MyWeb.class);
            intent.putExtra("from", "privacy");
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode==UPDATE_AVATAR_BY_ALBUM) {
                if (data != null) {
                    Uri uri = data.getData();
                    mAvatarUri = uri.toString();
                    mAvatarSdv.setImageURI(uri);
                }
            }
        }
    }

    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkSubmit();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    /**
     * 注册
     */
    private void register(String nickName, String phone, String password, String avatar) {
        if (avatar == null || avatar.length() == 0) {
            avatar = (Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + getResources().getResourcePackageName(R.mipmap.default_user_avatar) + "/"
                    + getResources().getResourceTypeName(R.mipmap.default_user_avatar) + "/"
                    + getResources().getResourceEntryName(R.mipmap.default_user_avatar))).toString();
        }

        String u2WxId = Constant.getWxId();

        int i = 1;

        String gender = getString(R.string.sex_female);
        String region = getString(R.string.country_china);
        String signature = getString(R.string.signature);

        AUser u2 = new AUser(phone, nickName,  password, avatar, u2WxId, gender, region, signature);
        db.getUserDao().insert(u2);

        MySP.getInstance().saveString("LOGIN", "yes");
        MySP.getInstance().saveString("PHONE",phone);
        MySP.getInstance().saveString("NICKNAME", nickName);
        MySP.getInstance().saveString("PASSWORD", password);
        MySP.getInstance().saveString("AVATAR",avatar);
        MySP.getInstance().saveString("UCHATID", u2WxId);
        MySP.getInstance().saveString("SIGNATURE", signature);
        MySP.getInstance().saveString("GENDER", gender);
        MySP.getInstance().saveString("REGION", region);



        Log.e("Register",  "  avatar: " + u2.getUserAvatarUri());
        // 登录成功后设置user和isLogin至sharedpreferences中

        startActivity(new Intent(Register.this, Welcome3Main.class));
        Snackbar.make(mLayout, "注册成功！", Snackbar.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 表单是否填充完成(昵称,手机号,密码,是否同意协议)
     */
    private void checkSubmit() {
        boolean nickNameHasText = mNickNameEt.getText().toString().length() > 0;
        boolean phoneHasText = mPhoneEt.getText().toString().length() > 0;
        boolean passwordHasText = mPasswordEt.getText().toString().length() > 0;
        if (nickNameHasText && phoneHasText && passwordHasText && mAgree) {
            // 注册按钮可用
            mRegisterBtn.setBackgroundColor(getColor(R.color.register_btn_bg_enable));
            mRegisterBtn.setTextColor(getColor(R.color.register_btn_text_enable));
            mRegisterBtn.setEnabled(true);
        } else {
            // 注册按钮不可用
            mRegisterBtn.setBackgroundColor(getColor(R.color.register_btn_bg_disable));
            mRegisterBtn.setTextColor(getColor(R.color.register_btn_text_disable));
            mRegisterBtn.setEnabled(false);
        }
    }
}