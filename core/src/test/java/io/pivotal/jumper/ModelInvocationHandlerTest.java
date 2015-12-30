package io.pivotal.jumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class, sdk=16)
public class ModelInvocationHandlerTest {
    @Test
    public void methodIsCalledOnOriginal() throws Throwable {
        Model model = new DefaultModel();

        TestableModelInvocationHandler subject = new TestableModelInvocationHandler(model);

        Object[] objects = new Object[]{};
        Method method = model.getClass().getMethods()[0];

        Object invokeResult = subject.invoke(new Object(), method, objects);

        assertThat(subject.getCalledArgs()).isEqualTo(objects);
        assertThat(subject.getCalledMethod()).isEqualTo(method);
        assertThat(invokeResult).isEqualTo(subject.getResult());
    }

    @Test
    public void invoke_sendsBroadcast() throws Throwable {
        DefaultModel model = new DefaultModel();

        Context mockContext = mock(Context.class);
        ModelInvocationHandler subject = new ModelInvocationHandler(DummyInterface.class, mockContext, model, "bob's data");

        subject.invoke(new Object(), model.getClass().getMethods()[0], new Object[]{new DefaultModel()});

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);
        verify(mockContext).sendBroadcast(captor.capture());

        Intent value = captor.getValue();
        assertThat(value.getSerializableExtra(JumperBroadcastReceiver.MODEL)).isEqualTo(model);
        assertThat(value.getAction()).isEqualTo("bob's data");
    }

    public class TestableModelInvocationHandler extends ModelInvocationHandler {
        private Method calledMethod;
        private Object[] calledArgs;
        private Object result;

        public TestableModelInvocationHandler(Model model) {
            super(DummyInterface.class, new Activity(), model, "");
            result = new Object();
        }

        @Override
        protected Object callMethodOnOriginal(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            calledMethod = method;
            calledArgs = args;
            return result;
        }

        public Method getCalledMethod() {
            return calledMethod;
        }

        public Object[] getCalledArgs() {
            return calledArgs;
        }

        public Object getResult() {
            return result;
        }
    }
}