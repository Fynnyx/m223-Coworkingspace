# Abschlussprojekt: Coworking Space

Coworking Space ist ein Projekt in welchem User erstellt werden können um später Buchungen und Kaffee hinzuzufügen. Es macht das führen des Coworking Spaces deutlich einfacher.

## Erste Schritte

1. Stelle sicher, dass Docker installiert ist und läuft.
1. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
1. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
1. Öffne das Projekt mit Visual Studio Code.
1. Öffne das Projekt im Entwicklungscontainer.
1. Starte das Projekt mit dem Command `mvn compile quarkus:dev` im Terminal.
1. Schaue die API auf http://localhost:8080/q/swagger-ui/ an.

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests werden die Test-Daten aus dem `import.sql`-File in die Datenbank geladen. Dieser werden beim Starten der Appliaktion schon hinzugefügt.


## Zugangsdaten
### Mitglied User
| Email           | Password |
|-----------------|----------|
| user@user.ch    | user     |

### Admin User
| Email           | Password |
|-----------------|----------|
| admin@admin.ch  | admin    |



# Weiters
## Mein Github
Mein Gituhub ist öffentlich und unter der URL
- https://github.com/Fynnyx/m223-Coworkingspace
erreichbar.