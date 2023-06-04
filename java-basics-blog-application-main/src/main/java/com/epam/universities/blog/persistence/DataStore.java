package com.epam.universities.blog.persistence;

import java.io.FileNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.tools.StandardJavaFileManager.PathFactory;

import com.epam.universities.blog.domain.*;

public interface DataStore {

	public void load() throws FileNotFoundException;

	public List<User> getAllUsers();

	public List<Following> getAllFollowings();
}
