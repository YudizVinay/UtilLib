# UtilLib  
<a href='https://bintray.com/vinay/android/UtilLib?source=watch' alt='Get automatic notifications about new "UtilLib" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
[ ![Download](https://api.bintray.com/packages/vinay/android/UtilLib/images/download.svg) ](https://bintray.com/vinay/android/UtilLib/_latestVersion)

Library contains Location, Run time Permission, Image chooser in Android Project

AAR File
https://github.com/YudizVinay/UtilLib/raw/master/utillib-1.3/utillib-1.3.aar


dialog with camera and gallery
and you can choose image from Camera directly without dialog with `ChooseType.REQUEST_CAPTURE_PICTURE`
or you can choose image from Gallery directly without dialog with `ChooseType.REQUEST_PICK_PICTURE`
```
UtilLib.getPhoto(mContext, ChooseType.REQUEST_ANY)
     .enqueue(new OnImageChooserListener() {
         @Override
         public void onImageChoose(String path) {
             Glide.with(MainActivity.this).load(new File(path)).into(iv);
         }
     });
```

Runtime Permission
```
UtilLib.getPermission(mContext, new String[]{ Manifest.permission.CAMERA })
    .enqueue(new PermissionResultCallback() {
        @Override
        public void onComplete(PermissionResponse permissionResponse) {
             Log.d("Tag", "Permission is "+(permissionResponse.isAllGranted() ? "Enable" : "Disable"));
        }
    });
```

get location from either GPS or Network
```
UtilLib.getLocationManager(mContext).getLocation(new OnLocationPickListener() {
    @Override
    public void getLastLocation(Location location) {
        Log.d("Tag", "lng:" + location.getLongitude() + " lat:" + location.getLatitude());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Tag", ""lng:" + location.getLongitude() + " lat:" + location.getLatitude());
    }

    @Override
    public void onError(String error) {
        Log.d("Tag", "Location Error." + error);
    }
});
```
