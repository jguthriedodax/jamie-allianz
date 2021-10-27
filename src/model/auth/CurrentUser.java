package model.auth;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope("request")
public class CurrentUser {

    @Getter @Setter private User user;
}