package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.human.Human;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Person extends Human {

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Connection userConnection;

    @Deprecated
    public Person() {
    }

    public Person(String name, LocalDate birthDate, Gender gender, User user, Connection userConnection) {
        super(name, birthDate, gender);
        this.user = user;
        this.userConnection = userConnection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Connection getUserConnection() {
        return userConnection;
    }

    public Connection getConnectionToUser() {
        return userConnection.inverseConnection();
    }

    public void setUserConnection(Connection userConnection) {
        this.userConnection = userConnection;
    }

    public void update(PersonForm personForm) {
        setName(personForm.name());
        setBirthDate(personForm.birthDate());
        setGender(personForm.gender());
        setUserConnection(personForm.userConnection());
    }
}
