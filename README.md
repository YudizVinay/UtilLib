# UtilLib
Library contains Location, Run time Permission, Image chooser in Android Project

AAR File
https://github.com/YudizVinay/UtilLib/raw/master/utilLib-1.3/utilLib-1.3.aar


dialog with camera and gallery
```
UtilLib.getPhoto(this, ChooseType.REQUEST_ANY)
     .enqueue(new OnImageChooserListener() {
         @Override
         public void onImageChoose(String path) {
             Glide.with(MainActivity.this).load(new File(path)).into(iv);
         }
     });
```

Runtime Permission
```
UtilLib.getPermission(this, new String[]{ Manifest.permission.CAMERA }, 12)
    .enqueue(new PermissionResultCallback() {
        @Override
        public void onComplete(PermissionResponse permissionResponse) {
             Log.d("Tag", "Permission is "+(permissionResponse.isAllGranted() ? "Enable" : "Disable"));
        }
    });
```

get location from either GPS or Network
```
UtilLib.getLocationManager(MainActivity.this).getLocation(new OnLocationPickListener() {
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