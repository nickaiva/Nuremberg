package nickaiva.sightseeing.nuremberg;

/**
 * Created by Nick on 20/5/2016.
 */
class MainDataDef {

    private final int image;
    private final String name;
    private final String info;

    public MainDataDef(int image, String name, String info) {

        this.image = image;
        this.name = name;
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

}
