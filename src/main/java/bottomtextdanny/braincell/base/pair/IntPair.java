package bottomtextdanny.braincell.base.pair;

/**
 * <p>Record implementation of {@link Tuple} that defaults {@link Integer} as left type.</p>
 *
 * @param <T> right element type.
 */
public record IntPair<T>(Integer left, T right) implements Tuple<Integer, T> {

    /**
     * <p>A fancy instantiation.</p>
     * @param left the {@code int} value that will be taken as {@link #left} value.
     * @param right the {@param <T>} value that will be taken as {@link #right} value.
     * @return a newly created instance with the given parameters.
     */
    public static <T> IntPair<T> of(int left, T right) {
        return new IntPair<>(left, right);
    }
}
