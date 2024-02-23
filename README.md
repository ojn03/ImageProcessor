For this extra credit assignment I implemented downsize and mask operations. Both of these are based around function Objects. Each function object is an ImageProcessorCommand with the method create(Model m) which returns the edited image. The Mask command takes a second command so that it can apply its mask to that command. This allows it to be versatile, and be used with any command. The mask and the image, however, must be the same dimensions to work properly.

Only Downsize has been exposed in the GUI, and works exactly like the other commands: Click the downsize command then choose dimensions and a new name. Its text syntax is similar:
Downsize <newHeight> <newWidth> <imgName> <newName>

Mask syntax is as follows:
-mask <maskImage> <imgFunction> <Function args> 
 for example, to use the diamond mask to brightnem the image "swing" by 20 and name it "swing20":
-mask diamondMask Brightness 20 swing swing20

