import { gql } from "apollo-angular";

const CREATE_BRAND = gql`
  mutation CreateBrand($brandDto: BrandDto){
    saveBrand(brandDto: $brandDto){
      id
      name
    }
  }
`;

const UPDATE_BRAND = gql`
  mutation UpdateBrand($id: Int, $brandDto: BrandDto){
    updateBrand(id: $id,brandDto: $brandDto){
      id
      name
    }
  }
`;

const DELETE_BRAND = gql`
  mutation DeleteBrand($id: Int){
    deleteBrand(id: $id){
      id
    }
  }
`;

export {CREATE_BRAND, UPDATE_BRAND, DELETE_BRAND};
