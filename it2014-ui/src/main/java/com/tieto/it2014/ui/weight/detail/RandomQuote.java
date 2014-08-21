package com.tieto.it2014.ui.weight.detail;

import java.util.Arrays;
import java.util.List;

public class RandomQuote {

    private static final List<String> positiveQuotes = Arrays.asList(
            "Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill",
            "I attribute my success to this: I never gave or took any excuse. –Florence Nightingale",
            "You miss 100% of the shots you don’t take. –Wayne Gretzky",
            "The most difficult thing is the decision to act, the rest is merely tenacity. –Amelia Earhart",
            "I am not a product of my circumstances. I am a product of my decisions. –Stephen Covey",
            "Either you run the day, or the day runs you. –Jim Rohn",
            "Whether you think you can or you think you can’t, you’re right. –Henry Ford",
            "The best revenge is massive success. –Frank Sinatra",
            "People often say that motivation doesn’t last. Well, neither does bathing.  That’s why we recommend it daily. –Zig Ziglar",
            "Life shrinks or expands in proportion to one’s courage. –Anais Nin",
            "There is only one way to avoid criticism: do nothing, say nothing, and be nothing. –Aristotle",
            "Ask and it will be given to you; search, and you will find; knock and the door will be opened for you. –Jesus",
            "The only person you are destined to become is the person you decide to be. –Ralph Waldo Emerson",
            "Go confidently in the direction of your dreams.  Live the life you have imagined. –Henry David Thoreau",
            "Believe you can and you’re halfway there. –Theodore Roosevelt",
            "Everything you’ve ever wanted is on the other side of fear. –George Addair",
            "Start where you are. Use what you have.  Do what you can. –Arthur Ashe",
            "Fall seven times and stand up eight. –Japanese Proverb",
            "You can’t fall if you don’t climb.  But there’s no joy in living your whole life on the ground. –Unknown",
            "Too many of us are not living our dreams because we are living our fears. –Les Brown",
            "Challenges are what make life interesting and overcoming them is what makes life meaningful. –Joshua J. Marine",
            "I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do. –Leonardo da Vinci",
            "Limitations live only in our minds.  But if we use our imaginations, our possibilities become limitless. –Jamie Paolinetti",
            "In order to succeed, your desire for success should be greater than your fear of failure. –Bill Cosby",
            "A person who never made a mistake never tried anything new. – Albert Einstein",
            "The person who says it cannot be done should not interrupt the person who is doing it. –Chinese Proverb",
            "The battles that count aren’t the ones for gold medals. The struggles within yourself–the invisible battles inside all of us–that’s where it’s at. –Jesse Owens",
            "It does not matter how slowly you go as long as you do not stop. –Confucius",
            "Dream big and dare to fail. –Norman Vaughan",
            "If you do what you’ve always done, you’ll get what you’ve always gotten. –Tony Robbins",
            "You may be disappointed if you fail, but you are doomed if you don’t try. –Beverly Sills",
            "Remember no one can make you feel inferior without your consent. –Eleanor Roosevelt",
            "When everything seems to be going against you, remember that the airplane takes off against the wind, not with it. –Henry Ford",
            "It’s not the years in your life that count. It’s the life in your years. –Abraham Lincoln",
            "“A bear, however hard he tries, grows tubby without exercise.” ― A.A. Milne, Winnie-the-Pooh"
    );

    private static final List<String> negativeQuotes = Arrays.asList(
            "You'll be so fat the only thing stopping you from going to the gym is the doorframe.",
            "You are so fat, you have to wear a watch on each arm - one for each time zone you're in.",
            "You have a wanted poster in every restourant around town.",
            "You will get so fat your belt will become the real equator",
            "Sattelites will soon start to orbit around you.",
            "You're gonna need you very own postal code.",
            "Your cereal bowl comes with a lifeguard.",
            "You don't need internet, you're already world wide!",
            "Hands up! Drop that burger!",
            "You always get gold medals in sprints. Becouse you are at the both ends of the track at the same time!"
    );

    public static String getPositive() {
        return positiveQuotes.get(randomWithRange(1, positiveQuotes.size() - 1));
    }

    public static String getNegative() {
        return negativeQuotes.get(randomWithRange(1, negativeQuotes.size()));
    }

    private static int randomWithRange(int min, int max) {
        int range = Math.abs(max - min) + 1;
        return (int) (Math.random() * range) + (min <= max ? min : max);
    }

}
