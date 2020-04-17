package com.bit;

import java.util.*;

public class Poker {
    //模拟创建一副牌，洗牌， 发牌
    static class Card {
        //不用 static 修饰的内部类，当创建 card 对象的时候，必须依靠 Poker 的实例才能创建
        //用 static 修饰的内部类，当创建 card 对象的时候，不须依靠 Poker 的实例创建（就可以在 Poker 的静态方法中创建实例）
        public String suit;//花色
        public String rank;//点数

        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            /*return "Card{" +
                    "suit='" + suit + '\'' +
                    ", rank='" + rank + '\'' +
                    '}';*/
            return String.format("(%s%s)", suit, rank);
        }
    }

    public static List<Card> buyPoker() {
        //1.构建一副牌
        String[] suits = {"♥", "♠", "♣", "♦"};
        List<Card> poker = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 2; j <10; j++) {
                Card card = new Card(suits[i], String.valueOf(j));
                poker.add(card);
            }
            poker.add(new Card(suits[i], "J"));
            poker.add(new Card(suits[i], "Q"));
            poker.add(new Card(suits[i], "K"));
            poker.add(new Card(suits[i], "A"));
        }
        return poker;
    }

    public static void shuffle(List<Card> poker) {
        //2.洗牌 shuffle
        //从后往前遍历 List, 每次取到一个牌之后，就和前面一个随机位置的牌交换
        Random random = new Random();
        for(int i = poker.size() - 1; i > 0; i--) {
            //产生一个[0，i)随机数
            int r = random.nextInt(i);
            swap(poker, i, r);
        }
    }
    private static void swap(List<Card> poker, int i, int j) {
        Card tmp = poker.get(i);
        poker.set(i, poker.get(j));
        poker.set(j, tmp);
    }

    public static void main(String[] args) {
        List<Card> poker = buyPoker();
        //System.out.println(poker);//打印一副牌

        //Collections.shuffle(poker);//系统自带方法
        shuffle(poker);
        //System.out.println(poker);//洗牌

        //3.发牌，把牌发给3个玩家，每人发5张
        //通过嵌套List来实现（每个玩家的手牌是一个 List, 多个玩家的手牌再放到一个 List 中）
        List<List<Card>> players = new ArrayList<List<Card>>();
        //players 中的元素个数就是玩家的总数
        players.add(new ArrayList<Card>());
        players.add(new ArrayList<Card>());
        players.add(new ArrayList<Card>());

        for(int cardIndex = 0; cardIndex < 5; cardIndex++) {
            for(int playerIndex = 0; playerIndex < 3; playerIndex++) {
                //模拟轮流发牌

                //先获取到玩家
                List<Card> player = players.get(playerIndex);
                //把 poker 中最前面的元素发给当前玩家
                Card toCard = poker.remove(0);
                player.add(toCard);
            }
        }

        //4.展示手牌，比较大小
        System.out.println("玩家1 的手牌：");
        System.out.println(players.get(0));
        System.out.println("玩家2 的手牌：");
        System.out.println(players.get(1));
        System.out.println("玩家3 的手牌：");
        System.out.println(players.get(2));
    }
}
