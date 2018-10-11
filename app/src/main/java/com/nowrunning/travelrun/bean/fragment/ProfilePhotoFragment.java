package com.nowrunning.travelrun.bean.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaygoo.widget.RangeSeekBar;
import com.nowrunning.travelrun.R;
import com.nowrunning.travelrun.activity.TravelPref;
import com.nowrunning.travelrun.activity.util.RoundedCornersTransformation2;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfilePhotoFragment extends Fragment {
    private View view;
    private TextView tv_age_range_value;
    private Typeface fontSemi;
    private TextView tv_back,tv_what_up,tv_what_up_desc,tv_edit_profile,tv_biogra,tv_biogra_detail;
    private TravelPref travelPref;
    private RelativeLayout rl_image1,rl_image2,rl_image3,rl_image4,rl_image5,rl_image6;
    public ProfilePhotoFragment() {
    }
    Context context;
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    private ProgressBar pg_icon;
    private ImageView image_1,image_2,image_3,image_4,image_5,image_6;
    private ImageView image_1a,image_2a,image_3a,image_4a,image_5a,image_6a;
    private ImageView iv_status_1,iv_status_2,iv_status_3,iv_status_4,iv_status_5,iv_status_6;
    private ImageView iv_status_1a,iv_status_2a,iv_status_3a,iv_status_4a,iv_status_5a,iv_status_6a;
    private TextView tv_1a,tv_2a,tv_3a,tv_4a,tv_5a,tv_6a;
    private  int GALLERY_REQUEST_1=1;
    private  int GALLERY_REQUEST_2=2;
    private  int GALLERY_REQUEST_3=3;
    private  int GALLERY_REQUEST_4=4;
    private  int GALLERY_REQUEST_5=5;
    private  int GALLERY_REQUEST_6=6;
    private  int radio=45;
    private  int margin=10;
    private  RelativeLayout.LayoutParams params;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_photo_fragment,container,false);
        pg_icon = (ProgressBar) view.findViewById(R.id.pg_icon);

        tv_back = (TextView) view.findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().finish();
            }
        });
        tv_edit_profile= (TextView) view.findViewById(R.id.tv_edit_profile);

        tv_what_up= (TextView) view.findViewById(R.id.tv_what_up);
        tv_what_up_desc= (TextView) view.findViewById(R.id.tv_what_up_desc);
        tv_biogra= (TextView) view.findViewById(R.id.tv_biogra);
        tv_biogra_detail= (TextView) view.findViewById(R.id.tv_biogra_detail);

        image_1 = (ImageView) view.findViewById(R.id.image_1);
        image_2 = (ImageView) view.findViewById(R.id.image_2);
        image_3 = (ImageView) view.findViewById(R.id.image_3);
        image_4 = (ImageView) view.findViewById(R.id.image_4);
        image_5 = (ImageView) view.findViewById(R.id.image_5);
        image_6 = (ImageView) view.findViewById(R.id.image_6);

        iv_status_1= (ImageView) view.findViewById(R.id.iv_status_1);
        iv_status_2= (ImageView) view.findViewById(R.id.iv_status_2);
        iv_status_3= (ImageView) view.findViewById(R.id.iv_status_3);
        iv_status_4= (ImageView) view.findViewById(R.id.iv_status_4);
        iv_status_5= (ImageView) view.findViewById(R.id.iv_status_5);
        iv_status_6= (ImageView) view.findViewById(R.id.iv_status_6);


        image_1a= (ImageView) view.findViewById(R.id.image_1a);
        image_2a= (ImageView) view.findViewById(R.id.image_2a);
        image_3a= (ImageView) view.findViewById(R.id.image_3a);
        image_4a= (ImageView) view.findViewById(R.id.image_4a);
        image_5a= (ImageView) view.findViewById(R.id.image_5a);
        image_6a= (ImageView) view.findViewById(R.id.image_6a);

        iv_status_1a= (ImageView) view.findViewById(R.id.iv_status_1a);
        iv_status_2a= (ImageView) view.findViewById(R.id.iv_status_2a);
        iv_status_3a= (ImageView) view.findViewById(R.id.iv_status_3a);
        iv_status_4a= (ImageView) view.findViewById(R.id.iv_status_4a);
        iv_status_5a= (ImageView) view.findViewById(R.id.iv_status_5a);
        iv_status_6a= (ImageView) view.findViewById(R.id.iv_status_6a);

        tv_1a= (TextView) view.findViewById(R.id.tv_1a);
        tv_2a= (TextView) view.findViewById(R.id.tv_2a);
        tv_2a= (TextView) view.findViewById(R.id.tv_2a);
        tv_3a= (TextView) view.findViewById(R.id.tv_3a);
        tv_4a= (TextView) view.findViewById(R.id.tv_4a);
        tv_5a= (TextView) view.findViewById(R.id.tv_5a);
        tv_6a= (TextView) view.findViewById(R.id.tv_6a);


        rl_image1= (RelativeLayout) view.findViewById(R.id.rl_image1);
        iv_status_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_1.setVisibility(View.GONE);
                iv_status_1.setVisibility(View.GONE);

                image_1a.setVisibility(View.VISIBLE);
                iv_status_1a.setVisibility(View.VISIBLE);
                tv_1a.setVisibility(View.VISIBLE);
            }
        });

        rl_image2= (RelativeLayout) view.findViewById(R.id.rl_image2);
        iv_status_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_2.setVisibility(View.GONE);
                iv_status_2.setVisibility(View.GONE);

                image_2a.setVisibility(View.VISIBLE);
                iv_status_2a.setVisibility(View.VISIBLE);
                tv_2a.setVisibility(View.VISIBLE);
            }
        });

        rl_image3= (RelativeLayout) view.findViewById(R.id.rl_image3);
        iv_status_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_3.setVisibility(View.GONE);
                iv_status_3.setVisibility(View.GONE);

                image_3a.setVisibility(View.VISIBLE);
                iv_status_3a.setVisibility(View.VISIBLE);
                tv_3a.setVisibility(View.VISIBLE);
            }
        });

        rl_image4= (RelativeLayout) view.findViewById(R.id.rl_image4);
        iv_status_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_4.setVisibility(View.GONE);
                iv_status_4.setVisibility(View.GONE);

                image_4a.setVisibility(View.VISIBLE);
                iv_status_4a.setVisibility(View.VISIBLE);
                tv_4a.setVisibility(View.VISIBLE);
            }
        });

        rl_image5= (RelativeLayout) view.findViewById(R.id.rl_image5);
        iv_status_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_5.setVisibility(View.GONE);
                iv_status_5.setVisibility(View.GONE);

                image_5a.setVisibility(View.VISIBLE);
                iv_status_5a.setVisibility(View.VISIBLE);
                tv_5a.setVisibility(View.VISIBLE);
            }
        });

        rl_image6= (RelativeLayout) view.findViewById(R.id.rl_image6);
        iv_status_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_6.setVisibility(View.GONE);
                iv_status_6.setVisibility(View.GONE);

                image_6a.setVisibility(View.VISIBLE);
                iv_status_6a.setVisibility(View.VISIBLE);
                tv_6a.setVisibility(View.VISIBLE);
            }
        });

        iv_status_1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_1);
            }
        });

        iv_status_2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_2);
            }
        });

        iv_status_3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_3);
            }
        });

        iv_status_4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_4);
            }
        });


        iv_status_5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_5);
            }
        });

        iv_status_6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST_6);
            }
        });


        travelPref= new TravelPref(getActivity());

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_medium.otf");
        Typeface fontRegular = Typeface.createFromAsset(getActivity().getAssets(),  "proximasoft_regular.otf");
        fontSemi = Typeface.createFromAsset(getActivity().getAssets(), "proximasoft_semibold.otf");

        tv_biogra.setTypeface(fontSemi);
        tv_biogra_detail.setTypeface(fontSemi);
        tv_what_up_desc.setTypeface(fontSemi);
        tv_what_up.setTypeface(fontSemi);
        tv_back.setTypeface(fontSemi);
        tv_edit_profile.setTypeface(fontSemi);

        /*CardView cv_language = (CardView)view.findViewById(R.id.cv_where_from);
        cv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });*/

        DisplayMetrics dm= new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightImage=dm.heightPixels;
        int widthImage=dm.widthPixels;

        //float factor = context.getResources().getDisplayMetrics().density;
        //params.width = (int)(70 * factor);
        //params.height = (int)(70 * factor);

        double dheight=(heightImage*0.30);
        int iHeight=(int)dheight;

        double dWidth=(widthImage*0.40);
        int iWidth=(int)dWidth;

        //iHeight=440;
        //iWidth=440;

        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iWidth, iHeight);
        params = new RelativeLayout.LayoutParams(iWidth, iHeight);
        params.setMargins(30, 0, 30, 0);

        image_1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_6.setScaleType(ImageView.ScaleType.CENTER_CROP);

        image_1.setLayoutParams(params);
        image_2.setLayoutParams(params);
        image_3.setLayoutParams(params);
        image_4.setLayoutParams(params);
        image_5.setLayoutParams(params);
        image_6.setLayoutParams(params);


        image_1a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_1a.setLayoutParams(params);

        image_2a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_2a.setLayoutParams(params);

        image_3a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_3a.setLayoutParams(params);

        image_4a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_4a.setLayoutParams(params);

        image_5a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_5a.setLayoutParams(params);

        image_6a.setScaleType(ImageView.ScaleType.CENTER_CROP);
        image_6a.setLayoutParams(params);

        //Uri uri = Uri.parse(surl);
        //StorageReference ref = FirebaseStorage.getInstance().getReference().child(uri.getPath());
        /*Glide.with(getActivity())
                .load(R.drawable.test4)
                .apply(new RequestOptions().override(params.width , params.height))
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(image_2);*/

        Picasso.with(getActivity())
                .load(R.drawable.test3)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_1);

        Picasso.with(getActivity())
                .load(R.drawable.test4)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_2);

        Picasso.with(getActivity())
                .load(R.drawable.test5)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_3);

        Picasso.with(getActivity())
                .load(R.drawable.test3)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_4);

        Picasso.with(getActivity())
                .load(R.drawable.test_blank)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit()
                .into(image_5);

        Picasso.with(getActivity())
                .load(R.drawable.test_blank)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_6);

        Picasso.with(getActivity())
                .load(R.drawable.test_blank)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_1a);

        Picasso.with(getActivity())
                .load(R.drawable.test_blank)
                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                .fit().centerCrop()
                .into(image_2a);


        /*Glide.with(this).load(R.drawable.test3)
                //.apply(bitmapTransform(new RoundedCornersTransformation2(radio,margin,RoundedCornersTransformation2.CornerType.ALL)))
                .apply(bitmapTransform(new RoundedCornersTransformation(radio,margin)))
                .apply(new RequestOptions().override(iWidth , iHeight))
                .into(image_4);*/



        return view;
    }



    private int TAKE_PHOTO_CODE = 125;
    private void takePhoto() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(getActivity().getApplicationContext())));
        intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "Snap the picture");
        intent.putExtra(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    private File getTempFile(Context context) {
        // it will return /sdcard/image.tmp
        final File path = new File(Environment.getExternalStorageDirectory(),
                context.getPackageName());

        if (!path.exists()) {
            path.mkdir();
        }
        return new File(path, "image.jpg");
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (resultCode == getActivity().RESULT_OK) {

                if (requestCode == TAKE_PHOTO_CODE) {
                    final File file = getTempFile(getActivity());
                    try {

                        Bundle bundle = new Bundle();

                        Uri imageCamera = Uri.fromFile(file);
                        //Intent intent = new Intent(getActivity().getApplicationContext(), ReceiptPhotoGalleryActivity.class);
                        //intent.putExtra("uriPhoto", imageCamera.toString());
                        //intent.putExtras(bundle);
                        //for test
                        //startActivityForResult(intent, 1);

                        //String mMediaString = imageCamera.toString();
                        String path = imageCamera.getPath();
                        int orientation;
                        ExifInterface exif = new ExifInterface(path);
                        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                        Log.e("orientation"," orientacion : "+orientString);

                        int orientation1 = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;
                        int value=0;
                        switch (orientation1) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                value=90;
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                value=180;
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                value=270;
                                break;
                            default:
                                value=0;
                                break;
                        }



                        //comment for test
                        //getImage(imageCamera.toString(),value);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if (requestCode == 99) {
                    Uri imageCamera;
                    imageCamera = data.getData();

                    Bundle bundle = new Bundle();
                    //bundle.putString("id",idHeight);

                    /*Intent intent = new Intent(getActivity().getApplicationContext(), HeightExistingFragment.class);
                    intent.putExtra("uriPhoto",imageCamera.toString());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1);*/

                }
                else if (requestCode == GALLERY_REQUEST_1) {
                    Uri selectedImage = data.getData();
                    try {
                        //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        //Uri urlBitmap=getImageUri(getActivity(),bitmap);
                       // image_1.setImageBitmap(bitmap);
                        image_1.setVisibility(View.VISIBLE);
                        iv_status_1.setVisibility(View.VISIBLE);

                        image_1a.setVisibility(View.GONE);
                        iv_status_1a.setVisibility(View.GONE);
                        tv_1a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_1);

                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }

                else if (requestCode == GALLERY_REQUEST_2) {
                    Uri selectedImage = data.getData();
                    try {
                        image_2.setVisibility(View.VISIBLE);
                        iv_status_2.setVisibility(View.VISIBLE);

                        image_2a.setVisibility(View.GONE);
                        iv_status_2a.setVisibility(View.GONE);
                        tv_2a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_2);
                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }

                else if (requestCode == GALLERY_REQUEST_3) {
                    Uri selectedImage = data.getData();
                    try {
                        image_3.setVisibility(View.VISIBLE);
                        iv_status_3.setVisibility(View.VISIBLE);

                        image_3a.setVisibility(View.GONE);
                        iv_status_3a.setVisibility(View.GONE);
                        tv_3a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_3);


                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }

                else if (requestCode == GALLERY_REQUEST_4) {
                    Uri selectedImage = data.getData();
                    try {
                        image_4.setVisibility(View.VISIBLE);
                        iv_status_4.setVisibility(View.VISIBLE);

                        image_4a.setVisibility(View.GONE);
                        iv_status_4a.setVisibility(View.GONE);
                        tv_4a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_4);


                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }

                else if (requestCode == GALLERY_REQUEST_5) {
                    Uri selectedImage = data.getData();
                    try {
                        image_5.setVisibility(View.VISIBLE);
                        iv_status_5.setVisibility(View.VISIBLE);

                        image_5a.setVisibility(View.GONE);
                        iv_status_5a.setVisibility(View.GONE);
                        tv_5a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_5);


                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }


                else if (requestCode == GALLERY_REQUEST_6) {
                    Uri selectedImage = data.getData();
                    try {
                        image_6.setVisibility(View.VISIBLE);
                        iv_status_6.setVisibility(View.VISIBLE);

                        image_6a.setVisibility(View.GONE);
                        iv_status_6a.setVisibility(View.GONE);
                        tv_6a.setVisibility(View.GONE);

                        Picasso.with(getActivity())
                                .load(selectedImage)
                                .transform(new jp.wasabeef.picasso.transformations.RoundedCornersTransformation(radio, margin))
                                .fit().centerCrop()
                                .into(image_6);


                    } catch (Exception e) {
                        Log.i("TAG", "Some exception " + e);
                    }

                }

            }

            else if(resultCode == getActivity().RESULT_CANCELED && data!=null){
            }

            else if (resultCode == 3) {
                takePhoto();
            }


        } catch (Exception e) {
            String error = e.getMessage();
            Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
