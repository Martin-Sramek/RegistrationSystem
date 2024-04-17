# Registrační Systém

Závěrečný (druhý) projekt ENGETO Java akademie.

Jedná se o Spring Boot aplikaci pracující s MySQL databází.

Struktura tabulky users v databázi:

| Sloupec    | Typ      | Ostatní                            |
| :--------- | :------- | :--------------------------------- |
| id         | Long     | PrimaryKey, NotNull, Autoincrement |
| name       | Varchar  | NotNull                            |
| surname    | Varchar  |                                    |
| person_id  | Varchar  | Unique, NotNull                    |
| uuid       | Varchar  | Unique, NotNull                    |

* person_id je unikátní, zadává se při vytváření uživatele, obsahuje přesně 12 znaků a může nabývat jen 20 různých hodnot (viz soubor dataPersonId.txt)
* uuid je unikátní a je generováno automaticky v aplikaci
* id je generováno automaticky systémem řízení báze dat

<br />

REST rozhraní aplikace umožňuje následující operace:

## /api/v1/user

### POST /api/v1/user
vytvoření nového uživatele přes JSON:\
{ name: string, surname: string, personID: string(12) }

vrátí JSON se všemi informacemi o nově vytvořeném uživateli:\
{ id: string, name: string, surname: string, personID: string , uuid: string }

### GET api/v1/user/{ID}
vrátí JSON obsahující id, name a surname uživatele na základě zadaného ID:\
{ id: string, name: string, surname: string }

### PUT api/v1/user
upraví name, surname, nebo obojí (id upravit nelze) přes JSON:\
{ id: string, name: string, surname: string }

vrátí JSON obsahující nové údaje o uživateli:\
{ id: string, name: string, surname: string }

### DELETE /api/v1/user/{ID}
smaže uživatele z databáze na základě zadaného ID
<br />
<br />
## /api/v1/users

### GET /api/v1/users
vrátí JSON obsahující seznam všech uživatelů v databázi (informace o uživateli zahrnují pouze id, name a surname):\
[{ id: string, name: string, surname: string }]

### GET /api/v1/users?detail=true
vrátí JSON obsahující seznam všech uživatelů v databázi (v tomto seznamu jsou dostupné všechny informace o uživateli):\
[{ id: string, name: string, surname: string, personID: string , uuid: string }]

### GET /api/v1/users/{ID}?detail=true
vrátí JSON obsahující všechny údaje o uživateli na základě zadaného ID:\
{ id: string, name: string, surname: string, personID: string , uuid: string }
<br />
<br />
<br />
K tomuto projektu jsou dále přiloženy:
* unit a integrační testy
* export databáze
* export z Postmana, kterým byla aplikace také testována
* soubor obsahující SQL pro vytvoření databáze a tabulky
* soubor obsahující PersonID kódy
