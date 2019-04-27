package xyz.khang.baitap_06042019;

import java.util.ArrayList;
import java.util.List;

public class Films {
    public static List<Film> list = new ArrayList<>();

    public static void InitFakeData() {
        list.clear();
        Film film;
        film = new Film();
        film.name = "Iron man";
        film.vietSub = "Người bàn ủi";
        film.rate = 5;
        list.add(film);

        film = new Film();
        film.name = "Iron man 2";
        film.vietSub = "Người bàn ủi 2";
        film.rate = 3;
        list.add(film);

        film = new Film();
        film.name = "Iron man 3";
        film.vietSub = "Người bàn ủi 3";
        film.rate = 4;
        list.add(film);

        film = new Film();
        film.name = "IP man";
        film.vietSub = "Iphone man";
        film.rate = 2;
        list.add(film);

        film = new Film();
        film.name = "Thor";
        film.vietSub = "Thỏ";
        film.rate = 1;
        list.add(film);

        film = new Film();
        film.name = "Thor 2";
        film.vietSub = "Thỏ 2";
        film.rate = 3;
        list.add(film);

        film = new Film();
        film.name = "Thor 3";
        film.vietSub = "Thỏ 3";
        film.rate = 5;
        list.add(film);

        film = new Film();
        film.name = "Deadpool";
        film.vietSub = "Đội trưởng Deadpool";
        film.rate = 4;
        list.add(film);
    }
}
