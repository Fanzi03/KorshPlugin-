{
  description = "dev shelll";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    systems.url = "github:nix-systems/default";
  };

  outputs =
    {
      nixpkgs,
      systems,
      ...
    }:
    let
      inherit (nixpkgs) lib;
      forEachPkgs = f: lib.genAttrs (import systems) (system: f nixpkgs.legacyPackages.${system});
    in
    {
      # packages = forEachPkgs (pkgs: {
      #   default = pkgs.callPackage ./package.nix { };
      # });

      devShells = forEachPkgs (pkgs: {
        default = pkgs.mkShell {
          packages = with pkgs; [
		openjdk21
    gradle
		lsof
		kotlin
          ];
          env.LD_LIBRARY_PATH=pkgs.lib.makeLibraryPath[
            pkgs.stdenv.cc.cc.lib
            pkgs.libz
          ];
          
          shellHook = ''
            alias l_server="./gradlew build && cd server &&  java -Xms4G -Xmx4G -jar paper-1.21.11.jar --nogui && cd .."
            alias k_server="kill -9 $(lsof -t -i:25565)"
          '';
        };
      });
    };
}
