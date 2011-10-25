package scrape

import org.jsoup.Jsoup

import collection.JavaConversions._


object Runner extends App {
  println("hello!")

  val url = "http://www.merton.gov.uk/environment/openspaces/play-areas/all-play-areas.htm"

  val doc = Jsoup.connect(url).get()

  println(doc.title())

  val links = doc.select("div#innerContentColumn > ul > li > a")

  for (link <- links) {
    val href = link.attr("href")
    val anchor = href.dropWhile('#' !=).drop(1)

    for (matching <- doc.getElementsByAttributeValue("name", anchor)) {
      println("found: " + matching.outerHtml())

      val heading = matching.parent()
      println("park name: " + heading.text())

      val img = heading.previousElementSibling()
      println("img: " + img.outerHtml())

      val address = heading.nextElementSibling()
      println("address: " + address.text())

      val descripton = address.nextElementSibling()
      println("description: " + descripton.text())

    }
  }

}