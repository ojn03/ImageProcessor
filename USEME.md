Each command is detailed as below:

Commands are case sensiive so type them exactly as they appear in the order they appear

Quit
Q

- exits the image processor

Upload <str: filepath> <str: imagename> 
Download <str: imagename> <str: filepath>

- filepath is the path at which to retrieve or download the file, image name is the name at which the image is saved

Visualize <str: component> <str: imgname> <str: newname>

- component is the component you want to visualize, imgname is the name of the image saved in the processor, newname is the name at which the visualized image will be saved
- <component> is one of: red, green, blue, value, intensity, luma

Brightness <int: adjustment> <str: imgname> <str: newname>

- adjustment is any integer value representing the amount which to brighten the image. negative values darken the image. imgname and newname are as explained above.


The following commands each require <imgname> and <newname>

Blur
- blurs the image

Sepia
- applies the sepia color transformation


Sharpen
- Sharpens edges

Grey
- Applies the Greyscale color transformation