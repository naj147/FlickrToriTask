package com.example.domainelayer.interactor;

import com.example.domainlayer.executor.PostExecutionThread;
import com.example.domainlayer.executor.ThreadExecutor;
import com.example.domainlayer.interactor.UseCase;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UseCaseTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private UseCaseTestClass useCase;
    private TestDisposableObserver<Object> testObserver;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.useCase = new UseCaseTestClass(mockThreadExecutor, mockPostExecutionThread);
        this.testObserver = new TestDisposableObserver<>();
        given(mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void testBuildUseCaseObservableReturnCorrectResult() {
        useCase.execute(testObserver, Params.EMPTY);
        assertThat(testObserver.valuesCount).isZero();
    }

    @Test
    public void testSubscriptionWhenExecutingUseCase() {
        useCase.execute(testObserver, Params.EMPTY);
        useCase.dispose();
        assertThat(testObserver.isDisposed()).isTrue();
    }

    @Test
    public void testShouldFailWhenExecuteWithNullObserver() {
        expectedException.expect(NullPointerException.class);
        useCase.execute(null, Params.EMPTY);
    }

    private static class UseCaseTestClass extends UseCase<Object, Params> {

        UseCaseTestClass(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        public Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }

        @Override
        public void execute(DisposableObserver<Object> observer, Params params) {
            super.execute(observer, params);
        }
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount = 0;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    private static class Params {
        private static Params EMPTY = new Params();
        private Params() {
        }
    }


}
