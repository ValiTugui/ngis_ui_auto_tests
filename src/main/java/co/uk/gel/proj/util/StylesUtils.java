package co.uk.gel.proj.util;


//import javafx.scene.text.FontWeight;

import java.awt.*;


public class StylesUtils {

    public static String convertFontFaceStringToCSSProperty(String fontFace) {
        //example convert property Bold to equivalent CSS property 700
      // return String.valueOf(FontWeight.findByName(fontFace).getWeight());
        return "String.valueOf(FontWeight.findByName(fontFace).getWeight())";

    }

    public static String convertFontColourStringToCSSProperty(String fontColor) {
         switch(fontColor) {
             case "#212b32":
                 return "rgba(33, 43, 50, 1)";
              case "#dd2509":
                 return "rgba(221, 37, 9, 1)";
             case "#005eb8":
                 return "rgba(0, 94, 184, 1)";
             default:
                 throw new IllegalStateException("Unexpected value: " + fontColor);
         }
    }

}
