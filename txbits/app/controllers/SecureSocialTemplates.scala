package controllers

import play.api.mvc.{ RequestHeader, Request }
import play.api.data.Form
import controllers.Registration.RegistrationInfo
import securesocial.core.{ SocialUser, SecuredRequest }
import play.api.Play.current
import play.twirl.api.Html
import play.twirl.api.Txt
import controllers.PasswordChange.ChangeInfo

object SecureSocialTemplates {
  def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)],
    msg: Option[String] = None): Html = {
    views.html.auth.login(form, msg)
  }

  def getTFATOTPPage[A](implicit request: Request[A], form: Form[String], msg: Option[String] = None): Html = {
    views.html.auth.TFAGoogle(form, msg)
  }

  def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html = {
    views.html.auth.Registration.signUp(form, token)
  }

  def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    views.html.auth.Registration.startSignUp(form)
  }

  def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html = {
    views.html.auth.Registration.startResetPassword(form)
  }

  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html = {
    views.html.auth.Registration.resetPasswordPage(form, token)
  }

  def getPasswordChangePage[A](implicit request: SecuredRequest[A], form: Form[ChangeInfo]): Html = {
    views.html.auth.passwordChange(form)
  }

  def getSignUpEmail(token: String, url: String): (Option[Txt], Option[Html]) = {
    (Some(views.txt.auth.mails.signUpEmail(token, url)), None)
  }

  def getAlreadyRegisteredEmail(email: String, url: String): (Option[Txt], Option[Html]) = {
    (Some(views.txt.auth.mails.alreadyRegisteredEmail(email, url)), None)
  }

  def getWelcomeEmail(user: SocialUser)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (Some(views.txt.auth.mails.welcomeEmail(user)), None)
  }

  def getSendPasswordResetEmail(email: String, url: String): (Option[Txt], Option[Html]) = {
    (Some(views.txt.auth.mails.passwordResetEmail(email, url)), None)
  }

  def getPasswordChangedNoticeEmail(email: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (Some(views.txt.auth.mails.passwordChangedNotice(email)), None)
  }

  def getNotAuthorizedPage[A](implicit request: Request[A]): Html = {
    views.html.auth.notAuthorized()
  }

}