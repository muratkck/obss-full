package week1.day4.withoutStream.example2;

public class City {
    private String name;
    private int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }
    public String getName() {
        return name;
    }
    public int getPopulation() {
        return population;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City name: " + name + ", population: " + population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o  instanceof City)) return false;
        City city = (City) o;
        return name.equals(city.name) && population == city.population;
    }
}
