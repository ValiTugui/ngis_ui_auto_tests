package co.uk.gel.proj.util;


public class StylesUtils {

    public static String convertFontFaceStringToCSSProperty(String fontFace) {
        //example convert property Bold to equivalent CSS property 700
        return String.valueOf(FontWeight.findByName(fontFace).getWeight());
    }

    public static String convertFontColourStringToCSSProperty(String fontColor) {
        switch (fontColor) {
            case "#212b32":
                return "rgba(33, 43, 50, 1)";
            case "#dd2509":
                return "rgba(221, 37, 9, 1)";
            case "#005eb8":
                return "rgba(0, 94, 184, 1)";
            case "#da291c":
                return "rgba(218, 41, 28, 1)";
            case "#e5f6f5":
                return "rgba(229, 246, 245, 1)";
            case "#fdf3e5":
                return "rgba(253, 243, 229, 1)";
            default:
                throw new IllegalStateException("Unexpected value: " + fontColor);
        }
    }

}
