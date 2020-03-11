package Testes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;

import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testing.FestSwingTestCaseTemplate;

public class CaixaUtils extends FestSwingTestCaseTemplate {

    public static void esperar(long milliSegundos) {
        try {
            Thread.sleep(milliSegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pressionarTeclas(int... keys) {
        try {
            esperar(300);
            Robot robot = new Robot();
            robot.setAutoWaitForIdle(true);
            for (int key : keys) {
                robot.keyPress(key);
            }
            for (int key : keys) {
                robot.keyRelease(key);
            }
            esperar(100);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void digitaTextoSemENTER(FrameFixture window, String texto) {
        esperar(200);
        digitaTextoSemENTER(window, texto, false);
    }

    private static void digitaTextoSemENTER(FrameFixture window, String texto, boolean caseSensitive) {
        if (texto != null && !texto.isEmpty()) {
            texto = !caseSensitive ? texto.toUpperCase() : texto;
            for (int i = 0; i < texto.length(); i++) {
                switch (texto.charAt(i)) {
                    case '*':
                        try {
                            window.pressKey(KeyEvent.VK_SHIFT);
                            window.robot.type(texto.charAt(i));
                        } finally {
                            window.releaseKey(KeyEvent.VK_SHIFT);
                            esperar(5);
                        }
                        break;
                    default:
                        window.robot.type(texto.charAt(i));
                        esperar(5);
                }
            }
        }
    }

    public static void escolherItemSeniorComboBoxIndice(FrameFixture window, String nomeComboBox, int opcao) {
        esperarComponenteAparecer(window, nomeComboBox);
        JComboBox<?> combo = (JComboBox<?>) window.robot.finder().findByName(nomeComboBox);
        combo.setSelectedIndex(opcao);
    }

    public static void escolherItemCampoFiltro(FrameFixture window, int opcao) {
        escolherItemSeniorComboBoxIndice(window, "Tipo", opcao);
    }

    public static void esperarComponenteAparecer(FrameFixture window, String compName) throws ComponentLookupException {
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (isComponenteVisivel(window, compName)) {
                return;
            }
            if (System.currentTimeMillis() - currentTimeMillis > 13000) {
                throw new ComponentLookupException("Não encontrou o componente " + compName);
            }
        }
    }

    public static boolean isComponenteVisivel(FrameFixture window, String compName) {
        try {
            return window.robot.finder().findByName(compName, true) != null;
        } catch (ComponentLookupException x) {
            return false;
        }
    }

}
