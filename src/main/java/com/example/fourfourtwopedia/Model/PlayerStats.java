    package com.example.fourfourtwopedia.Model;

    public class PlayerStats {
        public String username;
        public Integer totalMatchesPlayed;
        public Integer totalMatchesWins;
        public Integer totalMatchesDraw;

        public String getUsername() {
            return username;
        }
    
        public Integer getTotalMatchesPlayed() {
            return totalMatchesPlayed;
        }

        public Integer getTotalMatchesWins() {
            return totalMatchesWins;
        }

        public Integer getTotalMatchesDraw() {
            return totalMatchesDraw;
        }

        public Integer getTotalGoals() {
            return totalGoals;
        }

        public PlayerStats(String username, Integer totalMatchesPlayed, Integer totalMatchesWins, Integer totalMatchesDraw, Integer totalGoals) {
            this.username = username;
            this.totalMatchesPlayed = totalMatchesPlayed;
            this.totalMatchesWins = totalMatchesWins;
            this.totalMatchesDraw = totalMatchesDraw;
            this.totalGoals = totalGoals;
        }

        private Integer totalGoals;
    }
