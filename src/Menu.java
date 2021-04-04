import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Menu {
    Document document = null;

    public Menu() {
    }

    public String getStop(Element s) {
        String str = s.text();
        if (str.contains("-")) {
            String s1 = str.replace("-", "");
            return s1;
        } else return str;
    }

    public String[] getTrains(String station) throws IOException {
        if (station.equalsIgnoreCase("Аккаржа")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/akkarzha").get();
        }
        if (station.equalsIgnoreCase("Антонівка")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/antonivka").get();
        }
        if (station.equalsIgnoreCase("Апостолове")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/apostolove").get();
        }
        if (station.equalsIgnoreCase("Алтинівка")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/altynivka").get();
        }
        if (station.equalsIgnoreCase("бугаз")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/buhaz").get();
        }
        if (station.equalsIgnoreCase("букачівці")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/bukachivtsi").get();
        }
        if (station.equalsIgnoreCase("бурштин")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/burshtyn").get();
        }
        if (station.equalsIgnoreCase("березине")) {
            this.document = Jsoup.connect("https://proizd.ua/ua/station/berezyne").get();
        }


        Elements number = this.document.getElementsByClass("train__number");
        Elements train = this.document.getElementsByClass("train__route");
        Elements timeDepart = this.document.getElementsByClass("train__time train__time--departure");
        Elements timeArrive = this.document.getElementsByClass("train__time train__time--arrival");
        Elements timeStop = this.document.getElementsByClass("stay-time__time");
        String[] info = new String[3];

        for (int i = 0; i < number.size(); ++i) {
            if (i < 3) {
                info[i] = " Номер потяга : " + ((Element) number.get(i)).text() + "\n" + " Маршрут : "
                        + ((Element) train.get(i)).text() + "\n" + " Час прибуття : " + ((Element) timeArrive.get(i)).text()
                        + "\n" + " Час зупинки : " + getStop(timeStop.get(i)) + " хв " + "\n" + " Час відправлення : "
                        + ((Element) timeDepart.get(i)).text() ;
            }
        }
        return info;
    }


}

