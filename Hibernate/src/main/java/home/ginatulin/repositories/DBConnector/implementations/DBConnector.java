package home.ginatulin.repositories.DBConnector.implementations;

import org.hibernate.Session;

public interface DBConnector {
    Session getCurrentSession();
}
