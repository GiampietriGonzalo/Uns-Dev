public class C {

    private /*@ spec_public @*/ int x;

    public int m() {
        int  y = x;
        int  z = 0;
        while (y > 0) {
            z = z + x;
            y = y - 1;
        }
        return z;
    }
}
