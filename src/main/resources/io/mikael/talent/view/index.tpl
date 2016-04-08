package io.mikael.talent.view

/*
What's New

This page provides value in two different ways for two different audiences:

A. 1.0: For people who haven't logged in, it explains the concept behind the application.

   1.2: Also, fancy screenshots or concept pics.

B. 1.0:  For those who have logged in, it shows a "What's New" view of new
   (unseen) projects, new recommendations, etc.

   1.0: Under the new information, the user sees a list of currently open projects.

   1.2: As the user scrolls down the list of new information, projects and
   recommendations are marked as "seen".
 */

def title = "Talents"

def page = contents {
    div (class: "jumbotron") {
        div (class: "container") {
            h1 (class: "display-3", title)
            p ("Making happier people and easier projects.")
            p ("Browse through projects, recommend your friends for projects, of projects to your friends.")
            p ("Match people's skill sets and active interests with your open projects, to find motivated people.")
            p {
                a (class: "btn btn-primary btn-lg", role: "button", href: "/login", "Log in through Yammer &raquo;")
            }
        }
    }
}

layout 'io/mikael/talent/view/layout-main.tpl', true,
    title: title,
    bodyContents: page

