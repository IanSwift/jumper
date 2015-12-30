package io.pivotal.jumper;

public class DummyView implements JumperView {
    @Override
    public ViewDeserializer getViewDeserializer() {
        return new ViewDeserializer() {
            @Override
            public void deserialize(JumperView view, Model model) {

            }
        };
    }
}
