package io.mikael.talent.view

yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('person')
    }
    body {
        h1("Person ${username}")
        p("Current user: ${auth.userAuthentication.details.name}")
    }
}
