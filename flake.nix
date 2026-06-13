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
		lsof
		kotlin
          ];
          env.LD_LIBRARY_PATH=pkgs.lib.makeLibraryPath[
            pkgs.stdenv.cc.cc.lib
            pkgs.libz
          ];
        };
      });
    };
}
