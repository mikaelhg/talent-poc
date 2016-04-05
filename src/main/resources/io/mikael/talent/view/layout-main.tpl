yieldUnescaped '<!DOCTYPE html>'
html (lang: "en") {
    head {
        title (title)
        link (rel: "stylesheet", href: "/webjars/bootstrap/3.3.6/css/bootstrap.min.css")
        link (rel: "stylesheet", href: "/static/app.css")
    }

    body {

        nav (class: "navbar navbar-inverse navbar-static-top") {
            div (class: "container") {
                button (type: "button", class: "navbar-toggle collapsed", "data-toggle": "collapse", "data-target": "#navbar", "aria-expanded": "false", "aria-controls": "navbar") {
                    div(class: "navbar-header") {
                        span(class: "sr-only", "Toggle navigation")
                        span(class: "icon-bar")
                        span(class: "icon-bar")
                        span(class: "icon-bar")
                    }
                }
                a (class: "navbar-brand", href: "/", title)
                div (id: "navbar", class: "collapse navbar-collapse") {
                    ul (class: "nav navbar-nav") {
                        li(class: "active") { a(href: "#", "Home") }
                        li { a(href: "#about", "About") }
                    }

                    ul (class: "nav navbar-nav navbar-right") {
                        if (auth) {
                            li ("${auth?.userAuthentication?.details?.name}")
                            li { a ("Logout", href: "/logout") }
                        } else {
                            li { a ("Login", href: "/login") }
                        }
                    }

                }
            }
        }

        bodyContents()

        script (src: "/webjars/jquery/1.11.1/jquery.js")
        script (src: "/webjars/bootstrap/3.3.6/js/bootstrap.js")
    }
}
