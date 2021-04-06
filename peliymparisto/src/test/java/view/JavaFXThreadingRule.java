package view;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

class JavaFXThreadingRule implements TestRule {    
    /**
     * Flag for setting up the JavaFX, we only need to do this once for all tests.
     */
    private static boolean jfxIsSetup;

    @Override
    public Statement apply(Statement statement, Description description) {

        return new OnJFXThreadStatement(statement);
    }

    private static class OnJFXThreadStatement extends Statement {

        private final Statement statement;

        private OnJFXThreadStatement(Statement aStatement) {
            statement = aStatement;
        }

        private Throwable rethrownException = null;

        @Override
        public void evaluate() throws Throwable {

            if(!jfxIsSetup) {
                setupJavaFX();

                jfxIsSetup = true;
            }

            final CountDownLatch countDownLatch = new CountDownLatch(1);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        statement.evaluate();
                    } catch (Throwable e) {
                        rethrownException = e;
                    }
                    countDownLatch.countDown();
                }});

            countDownLatch.await();

            // if an exception was thrown by the statement during evaluation,
            // then re-throw it to fail the test
            if(rethrownException != null) {
                throw rethrownException;
            }
        }

        protected void setupJavaFX() throws InterruptedException {
            final CountDownLatch latch = new CountDownLatch(1);
            System.setProperty("java.awt.headless", "true");            
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // initializes JavaFX environment
                    new JFXPanel();                    
                    latch.countDown();
                }
            });

            latch.await();
        }

    }
}
