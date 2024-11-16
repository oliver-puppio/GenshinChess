package element;

public enum ElementState {
    Empty {
        public ElementState reaction(Element element) {
            switch (element) {
                case Hydro:
                    return HydroAttached;
                case Pyro:
                    return PyroAttached;
                case Cryo:
                    return CryoAttached;
                default:
                    return Empty;
            }
        }
    },
    HydroAttached {
        public ElementState reaction(Element element) {
            switch (element) {
                case Pyro:
                    return Evaporation;
                case Cryo:
                    return Freeze;
                case Time:
                    return Empty;
                default:
                    return HydroAttached;
            }
        }
    },
    PyroAttached {
        public ElementState reaction(Element element) {
            switch (element) {
                case Hydro:
                    return Evaporation;
                case Cryo:
                    return Melt;
                case Time:
                    return Empty;
                default:
                    return PyroAttached;
            }
        }
    },
    CryoAttached {
        public ElementState reaction(Element element) {
            switch (element) {
                case Hydro:
                    return Freeze;
                case Pyro:
                    return Melt;
                case Time:
                    return Empty;
                default:
                    return CryoAttached;
            }
        }
    },
    Evaporation {
        public ElementState reaction(Element element) {
            switch (element) {
                case Time:
                    return Empty;
                default:
                    return CryoAttached;
            }
        }

        @Override
        public double multiplier() {
            return 1.2;
        }
    },
    Melt {
        public ElementState reaction(Element element) {
            switch (element) {
                case Time:
                    return Empty;
                default:
                    return Melt;
            }
        }

        @Override
        public double multiplier() {
            return 1.3;
        }
    },
    Freeze {
        public ElementState reaction(Element element) {
            switch (element) {
                case Pyro:
                    return Melt;
                case Time:
                    return Empty;
                default:
                    return Freeze;
            }
        }

        @Override
        public double multiplier() {
            return 1.1;
        }
    };

    public double multiplier() {
        return 1;
    }

    abstract public ElementState reaction(Element element);
}
