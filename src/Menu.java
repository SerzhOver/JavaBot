import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Menu {
    Document document = null;

    public Menu() {
    }

    public String getArriveTime() {
        String time = "";

        Elements arrive = this.document.getElementsByClass("trip-time");

        for (int i = 0; i < arrive.size(); i++) {
            time = arrive.get(1).text();
        }
        return time;
    }

    public String[] getTrains(String firstMsg, String secondMsg) throws IOException {
        HashMap<String, Integer> stations = Station.getStations();

        Calendar date = GregorianCalendar.getInstance();
        date.add(Calendar.DATE, 7);

        String datePlus = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());

        while (true) {
            if (stations.containsKey(firstMsg) && stations.containsKey(secondMsg)) {
                this.document = Jsoup.connect("https://e-kvytok.ua/search?from="
                        + stations.get(firstMsg) + "&to=" + stations.get(secondMsg) + "&date=" + datePlus).get();
                break;
            }
        }

        Elements number = this.document.getElementsByClass("train-number");
        Elements train = this.document.getElementsByClass("trip-name");
        Elements depart = this.document.getElementsByClass("trip-time");
        Elements timeInWay = this.document.getElementsByClass("trip-duration");
        String[] info = new String[20];

        for (int i = 0; i <= number.size(); i++) {
            if (i < number.size() - 1) {
                info[i] = " Номер потяга : " + ((Element) number.get(i)).text() + "\n" + " Маршрут : "
                        + ((Element) train.get(i)).text() + "\n" + " Відправлення : "
                        + ((Element) depart.get(i)).text() + "\n" + " Час в дорозі : "
                        + ((Element) timeInWay.get(i)).text() + "\n" + " Прибуття : "
                        + getArriveTime();
            }
            if (number.size()==0){
                info[i]="За вашим напрямком нічого не знайдено";
                break;
            }
        }
        return info;

    }

}

