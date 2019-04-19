package oidc.web;

import org.junit.Test;
import org.springframework.security.saml.saml2.authentication.Assertion;
import org.springframework.security.saml.saml2.authentication.NameIdPrincipal;
import org.springframework.security.saml.saml2.authentication.Subject;
import org.springframework.security.saml.saml2.metadata.NameId;

import static org.junit.Assert.*;

public class FakeSamlAuthenticationFilterTest {

    @Test
    public void doFilterInternal() {
        Assertion assertion = new Assertion();
        Subject subject = new Subject();
        NameIdPrincipal principal = new NameIdPrincipal();

        principal.setFormat(NameId.UNSPECIFIED);
        principal.setValue("urn:admin");
        subject.setPrincipal(principal);
        assertion.setSubject(subject);


    }
}