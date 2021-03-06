package bottomtextdanny.braincell.base.function;

import java.util.Objects;
import java.util.function.Function;

public interface TriFunction<T, U, V, R> {

	R apply(T t, U u, V u1);
	
    default TriFunction<T, U, V, V> andThen(Function<? super R, ? extends V> after){
	Objects.requireNonNull(after);
	return(T t, U u, V v)->after.apply(apply(t,u, v));
	}
}
