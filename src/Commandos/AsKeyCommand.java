package Commandos;


import javafx.scene.input.KeyCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AsKeyCommand {
    String name();
    KeyCode[] keys();

}
