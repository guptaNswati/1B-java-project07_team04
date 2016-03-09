import java.awt.Color;

public class Legend{
        
        private String countryName;
        private Color legendColor;
             
        public Legend(String countryName, Color legendColor){
            this.countryName = countryName;
            this.legendColor = legendColor;
        }

        public Color getLegendColor() {
            return legendColor;
        }

        public void setLegendColor(Color legendColor) {
            this.legendColor = legendColor;
        }

        public String getCountryName()
        {
            // TODO Auto-generated method stub
            return this.countryName;
        }       
    }