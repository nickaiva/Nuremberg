package nickaiva.sightseeing.nuremberg;
/**
 * Created by Nick on 20/5/2016.
 */
class MainData {

    static final Integer[] imageArray = {
            R.drawable.sebaldus_icon,
            R.drawable.lorentz_icon,
            R.drawable.hauptmarkt_icon,
            R.drawable.burg_icon,
            R.drawable.durer_icon,
            R.drawable.apaulus_icon
    };
    static final Integer[] nameArray = {
            R.string.nameArraySebaldus,
            R.string.nameArrayLorentz,
            R.string.nameArrayFrauenkirche,
            R.string.nameArrayBurg,
            R.string.nameArrayDuerer,
            R.string.nameArrayStPaul
    };
    static final Integer[] infoArray = {

            R.string.infoArraySebaldus,
            R.string.infoArrayLorentz,
            R.string.infoArrayFrauenkirche,
            R.string.infoArrayBurg,
            R.string.infoArrayDuerer,
            R.string.infoArrayStPaul,

    };

    static final Integer[] detailImageArray = {
                    R.drawable.sebaldus,
                    R.drawable.lorentz,
                    R.drawable.hauptmarkt,
                    R.drawable.burg,
                    R.drawable.durer,
                    R.drawable.apaulus
            };
    static final Integer[] detailTextArray =
            {       R.string.detail_text_sebaldus,
                    R.string.detail_text_lorentz,
                    R.string.detail_text_hauptmarkt,
                    R.string.detail_text_burg,
                    R.string.detail_text_durer,
                    R.string.detail_text_apaulus
            };
    static final Integer[] detailWebLink =  {
                    R.string.detailWebLinkSebaldus,
                    R.string.detailWebLinkLorentz,
                    R.string.detailWebLinkFrauenkirche,
                    R.string.detailWebLinkBurg,
                    R.string.detailWebLinkDuerer,
                    R.string.detailWebLinkStPaul
            };
    //http://stackoverflow.com/questions/15042283/current-location-google-maps-link-to-directions
    static final String[] detailDirLink =  {
            "https://www.google.co.uk/maps?saddr=&daddr=49.455278,11.075833",
            "https://www.google.co.uk/maps?saddr=&daddr=49.451, 11.078056",
            "https://www.google.co.uk/maps?saddr=&daddr=49.453889, 11.076944",
            "https://www.google.co.uk/maps?saddr=&daddr=49.457778, 11.075833",
            "https://www.google.co.uk/maps?saddr=&daddr=49.457222,11.073889",
            "https://www.google.co.uk/maps?saddr=&daddr=49.447035,11.055416"
    };

    static final double[] latitudes = {
            49.455278,
            49.451,
            49.453889,
            49.457778,
            49.457222,
            49.447035
    };
    static final double[] longitudes = {
            11.075833,
            11.078056,
            11.076944,
            11.075833,
            11.073889,
            11.055416
    };

}
