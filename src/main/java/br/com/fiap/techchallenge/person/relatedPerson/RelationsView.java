package br.com.fiap.techchallenge.person.relatedPerson;

import br.com.fiap.techchallenge.user.User;
import br.com.fiap.techchallenge.user.UserView;

import java.util.List;

public record RelationsView(UserView user, List<RelatedPersonView> relatedPersons) {
    public RelationsView(User user) {
        this(new UserView(user), user.getRelatedPersons().stream().map(RelatedPersonView::new).toList());
    }
}
