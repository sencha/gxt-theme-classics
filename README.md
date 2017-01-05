# gxt-theme-classics
Extending GXT classic blue and gray theme appearances source. (Latest, 4.0.2)

## Extending blue or gray

1. Import the source into your source folder
 - Copy the source in directory `com.sencha.gxt.ui/src/main/java/*` module to your source folder with the same packaging naming. 
 
2. Add the source to your module descriptor with one of the below 

* Overriding the classic blue theme
```
<inherits name='com.sencha.gxt.theme.blueext.BlueExt' />
```


* Overriding the classic gray theme
```
<inherits name='com.sencha.gxt.theme.grayext.GrayExt' />
```

## Changes
When using appearanes, mark the changes in them and gss source files.
This helps with appearance upgrades when a major version shows up. 


## Support
If you get lost ask for more details from support. 
