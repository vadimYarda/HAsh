public class LazyString {
    private String source;
    private int start, end;
    private int hash;

    private LazyString() {}

    public LazyString(String source, int start, int end) {
        this.source = source;
        this.start = start;
        this.end = end;

        // Подсчет хеша
        hash = 0;
        for (int i = start; i < end; i++) {
            hash += source.charAt(i);
        }
    }

    public LazyString shiftRight() {
        LazyString shifted = new LazyString();
        shifted.source = source;
        shifted.start = start + 1;
        shifted.end = end + 1;

        // Обновление хеша
        shifted.hash = this.hash - source.charAt(start) + source.charAt(shifted.end - 1);

        return shifted;
    }

    public int length() {
        return end - start;
    }

    public boolean equals(LazyString that) {
        if (this.length() != that.length()) {
            return false;
        }

        for (int i = 0; i < length(); i++) {
            char myChar = source.charAt(start + i);
            char thatChar = source.charAt(that.start + i);
            if (myChar != thatChar) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LazyString that = (LazyString) o;
        return this.equals(that);
    }
}