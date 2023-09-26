package com.travelsio.hotelservice.parser;

import com.travelsio.hotelservice.model.Hotel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class HotelParser {

    private static final String SINGLE_HOTEL_SELECTOR = "div[data-testid=property-card]";
    private static final String HOTEL_NAME_SELECTOR = "div[data-testid=title]";
    private static final String STARS_SELECTOR = "div[aria-label*=nr]";
    private static final String RATING_SELECTOR = "div[aria-label^=Oceniony]";
    private static final String REVIEWS_NUMBER_SELECTOR = "div:containsOwn(opinii)";
    private static final String PRICE_SELECTOR = "span[data-testid=price-and-discounted-price]";
    private static final String PHOTO_URL_SELECTOR = "img[data-testid=image]";
    private static final String URL_SELECTOR = "a[data-testid=title-link]";

    public List<Hotel> parse(Document document) {

        List<Hotel> hotels = new ArrayList<>();
        Elements hotelsEl = document.select(SINGLE_HOTEL_SELECTOR);

        for (Element hotelEl : hotelsEl) {

            Element nameEl = hotelEl.select(HOTEL_NAME_SELECTOR).first();
            Element starsEl = hotelEl.select(STARS_SELECTOR).first();
            Element ratingEl = hotelEl.select(RATING_SELECTOR).first();
            Element reviewsEl = hotelEl.select(REVIEWS_NUMBER_SELECTOR).first();
            Element priceEl = hotelEl.select(PRICE_SELECTOR).first();
            Element photoUrlEl = hotelEl.select(PHOTO_URL_SELECTOR).first();
            Element urlEl = hotelEl.select(URL_SELECTOR).first();

            if (nameEl != null && starsEl != null && ratingEl != null && reviewsEl != null
                && priceEl != null && photoUrlEl != null && urlEl != null) {

                String hotelName = nameEl.text();
                Integer stars = formatStars(starsEl);
                Double reviewScore = formatReviewScore(ratingEl);
                String reviewsNumber = reviewsEl.text();
                BigDecimal price = formatPrice(priceEl);
                String photoUrl = photoUrlEl.attr("src");
                String url = urlEl.attr("href");

                Hotel hotel = new Hotel(hotelName, stars, reviewScore, reviewsNumber, price,
                    url, photoUrl);
                hotels.add(hotel);
            }
        }

        return hotels;
    }

    private BigDecimal formatPrice(Element priceEl) {
        String priceTxt = priceEl.text().replaceAll("[^0-9]", "");
        return new BigDecimal(priceTxt);
    }

    private double formatReviewScore(Element ratingEl) {
        return Double.parseDouble(ratingEl.text().replace(",", "."));
    }

    private Integer formatStars(Element starsEl) {
        String starsAttr = starsEl.attr("aria-label");
        String starsTxt = StringUtils.substringBetween(starsAttr, "nr ", " z");
        return Integer.parseInt(starsTxt);
    }
}
