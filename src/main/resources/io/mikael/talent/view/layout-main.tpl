yieldUnescaped '<!DOCTYPE html>'
html (lang: "en") {
    head {
        meta (charset: "utf-8")
        meta (name: "viewport", content: "width=device-width, initial-scale=1, shrink-to-fit=no")
        meta ("http-equiv": "x-ua-compatible", content: "ie=edge")
        title (title)
        link (rel: "stylesheet", href: "/webjars/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css")
        link (rel: "stylesheet", href: "/static/app.css")
    }

    body {

        nav(class: "navbar navbar-static-top navbar-dark bg-inverse") {
            a(class: "navbar-brand", href: "/", title)

            ul(class: "nav navbar-nav") {
                li(class: "nav-item active") {
                    a(class: "nav-link", href: "#", "Home")
                }
                li(class: "nav-item") {
                    a(class: "nav-link", href: "#about", "About")
                }
            }

            ul(class: "nav navbar-nav pull-xs-right") {
                if (auth) {
                    li(class: "nav-item", "${auth?.userAuthentication?.details?.name}")
                    li(class: "nav-item") { a(class: "nav-link", href: "/logout", "Logout") }
                } else {
                    li(class: "nav-item") { a(class: "nav-link", href: "/login", "Login") }
                }
            }

        }

        bodyContents()

        script (src: "/webjars/jquery/2.2.2/jquery.js")
        script (src: "/webjars/bootstrap/4.0.0-alpha.2/js/bootstrap.js")
    }
}
