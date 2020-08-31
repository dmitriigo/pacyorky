**Pacyorky app**

The Pacyorky app is a single application for monitoring of various events posted by Facebook (FB) users. The application uses calendar to visualize schedule of FB events.

**Used technologies.**
1. java 8.
2. Spring web, jpa.
3. PostgreSQL.
6. Frontend as Vue.js

**Usage:**

**Backend:**

Setup application with environment variables:

APPID = 'you app id in FB'

APPSECRET = 'you app secret in FB'

PG_PACYORKY_USER = 'login for postgre'

PG_PACYORKY_PASS = 'password for postgre'

DB_PACYORKY_HOST = 'full db path for jdbc'

Run application! Data will be updated every 30 minutes automatically.

**FrontEnd:**

Open /client/vue-client

run `npm install`

run `npm run serve`


Working app available at [Pacyorky site](http://pacyorky.ee)


