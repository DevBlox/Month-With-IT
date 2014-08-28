package com.tieto.it2014.ui.weight.detail;

import java.util.Arrays;
import java.util.List;

public class RandomQuote {

    private static final String[] bearQuote = new String[]{"A bear, however hard he tries, grows tubby without exercise.", "A.A. Milne, Winnie-the-Pooh"};

    private static final List<String[]> positiveQuotes = Arrays.asList(
            new String[]{"Whatever the mind of man can conceive and believe, it can achieve.", "Napoleon Hill"},
            new String[]{"I attribute my success to this: I never gave or took any excuse.", "Florence Nightingale"},
            new String[]{"You miss 100% of the shots you don’t take.", "Wayne Gretzky"},
            new String[]{"The most difficult thing is the decision to act, the rest is merely tenacity.", "Amelia Earhart"},
            new String[]{"I am not a product of my circumstances. I am a product of my decisions.", "Stephen Covey"},
            new String[]{"Either you run the day, or the day runs you.", "Jim Rohn"},
            new String[]{"Whether you think you can or you think you can’t, you’re right.", "Henry Ford"},
            new String[]{"The best revenge is massive success.", "Frank Sinatra"},
            new String[]{"People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily.", "Zig Ziglar"},
            new String[]{"Life shrinks or expands in proportion to one’s courage.", "Anais Nin"},
            new String[]{"There is only one way to avoid criticism: do nothing, say nothing, and be nothing.", "Aristotle"},
            new String[]{"Ask and it will be given to you; search, and you will find; knock and the door will be opened for you.", "Jesus"},
            new String[]{"The only person you are destined to become is the person you decide to be.", "Ralph Waldo Emerson"},
            new String[]{"Go confidently in the direction of your dreams.  Live the life you have imagined.", "Henry David Thoreau"},
            new String[]{"Believe you can and you’re halfway there.", "Theodore Roosevelt"},
            new String[]{"Everything you’ve ever wanted is on the other side of fear.", "George Addair"},
            new String[]{"Start where you are. Use what you have.  Do what you can.", "Arthur Ashe"},
            new String[]{"Fall seven times and stand up eight.", "Japanese Proverb"},
            new String[]{"You can’t fall if you don’t climb. But there’s no joy in living your whole life on the ground.", "Unknown"},
            new String[]{"Too many of us are not living our dreams because we are living our fears.", "Les Brown"},
            new String[]{"Challenges are what make life interesting and overcoming them is what makes life meaningful.", "Joshua J. Marine"},
            new String[]{"I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do.", "Leonardo da Vinci"},
            new String[]{"Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless.", "Jamie Paolinetti"},
            new String[]{"In order to succeed, your desire for success should be greater than your fear of failure.", "Bill Cosby"},
            new String[]{"A person who never made a mistake never tried anything new.", "Albert Einstein"},
            new String[]{"The person who says it cannot be done should not interrupt the person who is doing it.", "Chinese Proverb"},
            new String[]{"The battles that count aren’t the ones for gold medals. The struggles within yourself–the invisible battles inside all of us–that’s where it’s at.", "Jesse Owens"},
            new String[]{"It does not matter how slowly you go as long as you do not stop.", "Confucius"},
            new String[]{"Dream big and dare to fail.", "Norman Vaughan"},
            new String[]{"If you do what you’ve always done, you’ll get what you’ve always gotten.", "Tony Robbins"},
            new String[]{"You may be disappointed if you fail, but you are doomed if you don’t try.", "Beverly Sills"},
            new String[]{"Remember no one can make you feel inferior without your consent.", "Eleanor Roosevelt"},
            new String[]{"When everything seems to be going against you, remember that the airplane takes off against the wind, not with it.", "Henry Ford"},
            new String[]{"It’s not the years in your life that count. It’s the life in your years.", "Abraham Lincoln"},
            new String[]{"The human body is the best work of art.", "Jess C. Scott."},
            new String[]{"A fit, healthy body—that is the best fashion statement.", "Jess C. Scott."},
            bearQuote
    );

    private static final List<String[]> negativeQuotes = Arrays.asList(
            new String[]{"You don't need internet, you're already world wide!", "Somebody famous"},
            new String[]{"Hands up! Drop that burger!", "Somebody famous"},
            new String[]{"Exercise is a dirty word. Every time I hear it I wash my mouth out with chocolate.", "Charles M. Schulz"},
            new String[]{"The human body is the best work of art.", "Jess C. Scott"},
            new String[]{"The reason fat men are good natured is they can neither fight nor run.", "Theodore Roosevelt"},
            new String[]{"No one wakes up in the morning and says, 'I want to gain 150 pounds and I will start right now!", "Tricia Cunningham"},
            new String[]{"Getting fit is all about mind over matter. I don't mind, so it doesn't matter.", "Adam Hargreaves"},
            new String[]{"It's easier to stay in shape if you never let yourself get out of shape in the first place.", "Bill Loguidice"},
            new String[]{"Cricket is only 30 per cent physical and 70 per cent mental. South Africans train 120 per cent physically.", "Boeta Dippenaar"},
            new String[]{"Dieting is the only game where you win when you lose!", "Karl Lagerfeld"},
            new String[]{"If you don’t make mistakes, you aren’t really trying. ", "Somebody famous"},
            new String[]{"Take care of your body. It's the only place you have to live.", "Jim Rohn"},
            bearQuote
    );

    private static final List<String> helloMesages = Arrays.asList(
            "Hello, ",
            "Bonjour, ",
            "Shalom, ",
            "Buonguiorno, ",
            "おはよう, ",
            "안녕하세요, "
    );

    public static String[] getPositive() {
        return positiveQuotes.get(randomWithRange(0, positiveQuotes.size() - 1));
    }

    public static String[] getNegative() {
        return negativeQuotes.get(randomWithRange(0, negativeQuotes.size() - 1));
    }

    public static String getHelloMsg() {
        return helloMesages.get(randomWithRange(0, helloMesages.size() - 1));
    }

    private static int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

}
