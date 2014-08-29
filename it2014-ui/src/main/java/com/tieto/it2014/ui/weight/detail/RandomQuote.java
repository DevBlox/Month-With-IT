package com.tieto.it2014.ui.weight.detail;

import java.util.Arrays;
import java.util.List;

public class RandomQuote {

    private static final String[] BEAR_QUOTE = getQuoteString("A bear, however hard he tries, grows tubby without exercise.", "A.A. Milne, Winnie-the-Pooh");
    private static final String QUOTE_AUTHOR = "Somebody famous";

    private static final List<String[]> POSITIVE_QUOTES = Arrays.asList(
            getQuoteString("Whatever the mind of man can conceive and believe, it can achieve.", "Napoleon Hill"),
            getQuoteString("I attribute my success to this: I never gave or took any excuse.", "Florence Nightingale"),
            getQuoteString("You miss 100% of the shots you don’t take.", "Wayne Gretzky"),
            getQuoteString("The most difficult thing is the decision to act, the rest is merely tenacity.", "Amelia Earhart"),
            getQuoteString("I am not a product of my circumstances. I am a product of my decisions.", "Stephen Covey"),
            getQuoteString("Either you run the day, or the day runs you.", "Jim Rohn"),
            getQuoteString("Whether you think you can or you think you can’t, you’re right.", "Henry Ford"),
            getQuoteString("The best revenge is massive success.", "Frank Sinatra"),
            getQuoteString("People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily.", "Zig Ziglar"),
            getQuoteString("Life shrinks or expands in proportion to one’s courage.", "Anais Nin"),
            getQuoteString("There is only one way to avoid criticism: do nothing, say nothing, and be nothing.", "Aristotle"),
            getQuoteString("Ask and it will be given to you; search, and you will find; knock and the door will be opened for you.", "Jesus"),
            getQuoteString("The only person you are destined to become is the person you decide to be.", "Ralph Waldo Emerson"),
            getQuoteString("Go confidently in the direction of your dreams.  Live the life you have imagined.", "Henry David Thoreau"),
            getQuoteString("Believe you can and you’re halfway there.", "Theodore Roosevelt"),
            getQuoteString("Everything you’ve ever wanted is on the other side of fear.", "George Addair"),
            getQuoteString("Start where you are. Use what you have.  Do what you can.", "Arthur Ashe"),
            getQuoteString("Fall seven times and stand up eight.", "Japanese Proverb"),
            getQuoteString("You can’t fall if you don’t climb. But there’s no joy in living your whole life on the ground.", "Unknown"),
            getQuoteString("Too many of us are not living our dreams because we are living our fears.", "Les Brown"),
            getQuoteString("Challenges are what make life interesting and overcoming them is what makes life meaningful.", "Joshua J. Marine"),
            getQuoteString("I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do.", "Leonardo da Vinci"),
            getQuoteString("Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless.", "Jamie Paolinetti"),
            getQuoteString("In order to succeed, your desire for success should be greater than your fear of failure.", "Bill Cosby"),
            getQuoteString("A person who never made a mistake never tried anything new.", "Albert Einstein"),
            getQuoteString("The person who says it cannot be done should not interrupt the person who is doing it.", "Chinese Proverb"),
            getQuoteString("The battles that count aren’t the ones for gold medals. The struggles within yourself–the invisible battles inside all of us–that’s where it’s at.", "Jesse Owens"),
            getQuoteString("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
            getQuoteString("Dream big and dare to fail.", "Norman Vaughan"),
            getQuoteString("If you do what you’ve always done, you’ll get what you’ve always gotten.", "Tony Robbins"),
            getQuoteString("You may be disappointed if you fail, but you are doomed if you don’t try.", "Beverly Sills"),
            getQuoteString("Remember no one can make you feel inferior without your consent.", "Eleanor Roosevelt"),
            getQuoteString("When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.", "Henry Ford"),
            getQuoteString("It’s not the years in your life that count. It’s the life in your years.", "Abraham Lincoln"),
            getQuoteString("The human body is the best work of art.", "Jess C. Scott"),
            getQuoteString("A fit, healthy body—that is the best fashion statement.", "Jess C. Scott"),
            BEAR_QUOTE
    );

    private static final List<String[]> NEGATIVE_QUOTES = Arrays.asList(
            getQuoteString("You don't need internet, you're already world wide!", QUOTE_AUTHOR),
            getQuoteString("Hands up! Drop that burger!", QUOTE_AUTHOR),
            getQuoteString("Exercise is a dirty word. Every time I hear it I wash my mouth out with chocolate.", "Charles M. Schulz"),
            getQuoteString("The human body is the best work of art.", "Jess C. Scott"),
            getQuoteString("The reason fat men are good natured is they can neither fight nor run.", "Theodore Roosevelt"),
            getQuoteString("No one wakes up in the morning and says, 'I want to gain 150 pounds and I will start right now!", "Tricia Cunningham"),
            getQuoteString("Getting fit is all about mind over matter. I don't mind, so it doesn't matter.", "Adam Hargreaves"),
            getQuoteString("It's easier to stay in shape if you never let yourself get out of shape in the first place.", "Bill Loguidice"),
            getQuoteString("Cricket is only 30 per cent physical and 70 per cent mental. South Africans train 120 per cent physically.", "Boeta Dippenaar"),
            getQuoteString("Dieting is the only game where you win when you lose!", "Karl Lagerfeld"),
            getQuoteString("If you don’t make mistakes, you aren’t really trying. ", QUOTE_AUTHOR),
            getQuoteString("Take care of your body. It's the only place you have to live.", "Jim Rohn"),
            BEAR_QUOTE
    );

    private static String[] getQuoteString(String quote, String title) {
        return new String[]{quote, title};
    }

    private static final List<String> HELLO_MESSAGES = Arrays.asList(
            "Hello, ",
            "Bonjour, ",
            "Shalom, ",
            "Buonguiorno, ",
            "おはよう, ",
            "안녕하세요, "
    );

    public static String[] getPositive() {
        return POSITIVE_QUOTES.get(randomWithRange(0, POSITIVE_QUOTES.size() - 1));
    }

    public static String[] getNegative() {
        return NEGATIVE_QUOTES.get(randomWithRange(0, NEGATIVE_QUOTES.size() - 1));
    }

    public static String getHelloMsg() {
        return HELLO_MESSAGES.get(randomWithRange(0, HELLO_MESSAGES.size() - 1));
    }

    private static int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

}
