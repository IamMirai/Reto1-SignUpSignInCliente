/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import main.ApplicationFXSignUpTest;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.base.WindowMatchers;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author Julen
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpVControllerTest extends ApplicationTest {

    private Text labelInvalidUser;
    private Text labelInvalidEmail;
    private Text labelInvalidName;
    private Text labelInvalidPassword;
    private Text labelInvalidConfirmPassword;
    private TextField textFieldUsername;
    private TextField textFieldEmail;
    private TextField textFieldName;
    private PasswordField passwordField;
    private PasswordField passwordFieldConfirm;
    private TextField textFieldPassword;
    private TextField textFieldConfirmPassword;
    private Button buttonSignIn;

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFXSignUpTest.class);
    }

    /**
     * Test of the initial stage of the signup view
     */
    @Test
    public void testA_InitialStage() {
        verifyThat("#passwordField", isEnabled());
        verifyThat("#passwordFieldConfirm", isEnabled());
        verifyThat("#textFieldUsername", hasText(""));
        verifyThat("#textFieldEmail", hasText(""));
        verifyThat("#textFieldName", hasText(""));
        verifyThat("#passwordField", hasText(""));
        verifyThat("#passwordFieldConfirm", hasText(""));
        verifyThat("#textFieldPassword", hasText(""));
        verifyThat("#textFieldConfirmPassword", hasText(""));
        verifyThat("#buttonSignUp", isEnabled());
        verifyThat("#buttonSignIn", isEnabled());

    }

    /**
     * Test if the labels appear when all fields are empty and the SignUp button
     * is pressed
     */
    @Test
    public void testB_FieldsEmpty() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        labelInvalidName = lookup("#labelInvalidName").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        clickOn("#buttonSignUp");
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());
        assertEquals("Name is empty", labelInvalidName.getText());
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());
    }

    /**
     * Tests for the username TextField
     */
    @Test
    public void testC_UsernameIsInvalid() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        textFieldUsername = lookup("#textFieldUsername").query();
        clickOn("#textFieldUsername");
        write("test test");
        clickOn("#textFieldEmail");
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());

        clickOn("#textFieldUsername");
        eraseText(9);
        write("01234567890123456789012345");
        clickOn("#textFieldEmail");
        assertTrue(textFieldUsername.getText().length() <= 25);

        doubleClickOn("#textFieldUsername").push(KeyCode.DELETE);
        write("Test1");
        assertEquals("", labelInvalidUser.getText());
    }

    /**
     * Tests for the textFieldEmail
     */
    @Test
    public void testD_EmailIsInvalid() {
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        textFieldEmail = lookup("#textFieldEmail").query();
        clickOn("#textFieldEmail");
        write("test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(4);
        write("test@test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(9);
        write("test.test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(9);
        write("test@test.test");
        clickOn("#textFieldName");
        assertEquals("", labelInvalidEmail.getText());
    }
    
    
    /**
     * Tests for the textFieldName
     */
    @Test
    public void testE_NameisValid() {
        clickOn("#textFieldName");
        write("test");
        clickOn("#textFieldEmail");
        assertEquals("", labelInvalidName.getText());
    }

    /**
     * Tests for the passwordField
     */
    @Test
    public void testF_PasswordIsInvalid() {
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        passwordField = lookup("#passwordField").query();
        clickOn("#passwordField");
        write("test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#passwordField");
        eraseText(4);
        write("test test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#passwordField");
        eraseText(9);
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test if both of the password fields share the same text
     */
    @Test
    public void testG_ConfirmPasswordIsInvalid() {
        labelInvalidConfirmPassword = lookup("#labelInvalidConfirmPassword").query();
        passwordField = lookup("#passwordField").query();
        passwordFieldConfirm = lookup("#passwordFieldConfirm").query();
        clickOn("#passwordFieldConfirm");
        write("test");
        clickOn("#passwordField");
        assertEquals("These passwords didn’t match", labelInvalidConfirmPassword.getText());

        clickOn("#passwordFieldConfirm");
        eraseText(4);
        write(passwordField.getText());
        clickOn("#passwordField");
        assertEquals("", labelInvalidConfirmPassword.getText());
        eraseText(9);
        clickOn("#passwordFieldConfirm");
        eraseText(9);
    }

    /**
     * Test if ButtonShowHide makes visible textFieldPassword
     */
    @Test
    public void testH_showHide() {
        doubleClickOn("#ButtonShowHide");
        verifyThat("#textFieldPassword", isVisible());
    }

    /**
     * Tests for the textFieldPassword
     */
    @Test
    public void testI_textFieldPassword() {
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        textFieldPassword = lookup("#textFieldPassword").query();
        clickOn("#textFieldPassword");
        write("test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#textFieldPassword");
        eraseText(4);
        write("test test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#textFieldPassword");
        eraseText(9);
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test if ButtonShowHideConfirm makes visible textFieldConfirmPassword
     */
    @Test
    public void testJ_showHideConfirm() {
        doubleClickOn("#ButtonShowHideConfirm");
        verifyThat("#textFieldConfirmPassword", isVisible());
    }

    /**
     * Tests for the textFieldConfirmPassword
     */
    @Test
    public void testK_textFieldConfirmPassword() {
        labelInvalidConfirmPassword = lookup("#labelInvalidConfirmPassword").query();
        textFieldPassword = lookup("#textFieldPassword").query();
        textFieldConfirmPassword = lookup("#textFieldConfirmPassword").query();
        clickOn("#textFieldConfirmPassword");
        write("test");
        clickOn("#textFieldPassword");
        assertEquals("These passwords didn’t match", labelInvalidConfirmPassword.getText());

        clickOn("#textFieldConfirmPassword");
        eraseText(4);
        write(textFieldPassword.getText());
        clickOn("#textFieldPassword");
        assertEquals("", labelInvalidConfirmPassword.getText());
    }

    /**
     * Test to see if a user can go to the sign in view
     */
    @Test
    public void testL_goSignIn() {
        clickOn("#buttonSignIn");
        verifyThat(window("My Window"), WindowMatchers.isShowing());
    }

    /**
     * Go back to the SignUp view
     */
    @Test
    public void testN_goBackToSignUp() {
        clickOn("#buttonSignUp");
    }

    /**
     * Test to see if a user can sign up
     */
    @Test
    public void testM_signUnTest() {
        clickOn("#buttonSignUp");
        verifyThat(window("My Window"), WindowMatchers.isShowing());
    }
}
